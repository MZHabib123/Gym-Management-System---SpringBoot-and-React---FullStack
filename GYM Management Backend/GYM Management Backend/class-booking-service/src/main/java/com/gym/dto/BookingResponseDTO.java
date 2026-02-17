package com.gym.dto;

import java.time.LocalDateTime;

public class BookingResponseDTO {

    private Long id;
    private Long sessionId;
    private Long memberId;
    private LocalDateTime bookingDate;

    public BookingResponseDTO() {
    }

    public BookingResponseDTO(Long id, Long sessionId, Long memberId, LocalDateTime bookingDate) {
        this.id = id;
        this.sessionId = sessionId;
        this.memberId = memberId;
        this.bookingDate = bookingDate;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
