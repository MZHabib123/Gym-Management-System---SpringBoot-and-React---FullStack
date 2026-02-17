package com.gym.service;

import com.gym.dto.BookingRequestDTO;
import com.gym.dto.BookingResponseDTO;

import java.util.List;

public interface BookingService {

    BookingResponseDTO createBooking(BookingRequestDTO requestDTO);

    List<BookingResponseDTO> getBookingsByMember(Long memberId);

    void cancelBooking(Long bookingId);
}
