package com.gym.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.gym.dto.BookingRequestDTO;
import com.gym.dto.BookingResponseDTO;
import com.gym.dto.MemberDTO;
import com.gym.entity.Booking;
import com.gym.entity.ClassSession;
import com.gym.exception.BadRequestException;
import com.gym.exception.ResourceNotFoundException;
import com.gym.repository.BookingRepository;
import com.gym.repository.ClassSessionRepository;

import reactor.core.publisher.Mono;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ClassSessionRepository sessionRepository;
    private final WebClient webClient;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              ClassSessionRepository sessionRepository,
                              WebClient webClient) {
        this.bookingRepository = bookingRepository;
        this.sessionRepository = sessionRepository;
        this.webClient = webClient;
    }

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO requestDTO) {

        // 1️⃣ Validate session exists
        ClassSession session = sessionRepository.findById(requestDTO.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session not found with id " + requestDTO.getSessionId()));

        // 2️⃣ Validate member exists (Remote call)
        MemberDTO member;

        try {
            member = webClient.get()
                    .uri("http://localhost:8081/members/{id}", requestDTO.getMemberId())
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            response -> Mono.error(new ResourceNotFoundException(
                                    "Member not found with id " + requestDTO.getMemberId())))
                    .bodyToMono(MemberDTO.class)
                    .block();

        } catch (WebClientResponseException ex) {
            throw new BadRequestException("Member service error: " + ex.getMessage());
        }

        if (member == null) {
            throw new ResourceNotFoundException(
                    "Member not found with id " + requestDTO.getMemberId());
        }

        // 3️⃣ Prevent duplicate booking
        boolean alreadyBooked = bookingRepository
                .existsBySessionIdAndMemberId(
                        requestDTO.getSessionId(),
                        requestDTO.getMemberId());

        if (alreadyBooked) {
            throw new BadRequestException("Member already booked this session");
        }

        // 4️⃣ Check capacity
        long bookedCount = bookingRepository.countBySessionId(session.getId());

        if (bookedCount >= session.getCapacity()) {
            throw new BadRequestException("Session is full");
        }

        // 5️⃣ Save booking
        Booking booking = new Booking();
        booking.setSessionId(session.getId());
        booking.setMemberId(requestDTO.getMemberId());
        booking.setBookingDate(LocalDateTime.now());

        Booking saved = bookingRepository.save(booking);

        return mapToResponse(saved);
    }

    @Override
    public List<BookingResponseDTO> getBookingsByMember(Long memberId) {

        return bookingRepository.findByMemberId(memberId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Booking not found with id " + bookingId));

        bookingRepository.delete(booking);
    }

    private BookingResponseDTO mapToResponse(Booking booking) {

        return new BookingResponseDTO(
                booking.getId(),
                booking.getSessionId(),
                booking.getMemberId(),
                booking.getBookingDate()
        );
    }
}
