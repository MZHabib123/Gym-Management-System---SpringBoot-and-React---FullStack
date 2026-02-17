package com.gym.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long sessionId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    public Booking() {
    }

    public Booking(Long id, Long sessionId, Long memberId, LocalDateTime bookingDate) {
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
