package com.gym.dto;

import jakarta.validation.constraints.NotNull;

public class AttendanceRequestDTO {

    @NotNull(message = "Member ID is required")
    private Long memberId;

    public AttendanceRequestDTO() {
    }

    public AttendanceRequestDTO(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
