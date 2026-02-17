package com.gym.dto;

import java.time.LocalDateTime;

public class AttendanceResponseDTO {

    private Long id;
    private Long memberId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public AttendanceResponseDTO() {
    }

    public AttendanceResponseDTO(Long id, Long memberId,
                                 LocalDateTime checkInTime,
                                 LocalDateTime checkOutTime) {
        this.id = id;
        this.memberId = memberId;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }
}
