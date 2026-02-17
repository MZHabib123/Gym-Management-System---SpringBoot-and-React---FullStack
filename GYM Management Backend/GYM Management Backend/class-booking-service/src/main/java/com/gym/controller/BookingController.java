package com.gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.dto.BookingRequestDTO;
import com.gym.dto.BookingResponseDTO;
import com.gym.service.BookingService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(
            @Valid @RequestBody BookingRequestDTO requestDTO) {

        return ResponseEntity.ok(bookingService.createBooking(requestDTO));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByMember(
            @PathVariable Long memberId) {

        return ResponseEntity.ok(bookingService.getBookingsByMember(memberId));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {

        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
