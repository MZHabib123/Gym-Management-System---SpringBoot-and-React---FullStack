package com.gym.dto;

import jakarta.validation.constraints.NotNull;

public class BookingRequestDTO {

    @NotNull(message = "Session id is required")
    private Long sessionId;

    @NotNull(message = "Member id is required")
    private Long memberId;

    public BookingRequestDTO() {
    }

    public BookingRequestDTO(Long sessionId, Long memberId) {
        this.sessionId = sessionId;
        this.memberId = memberId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
