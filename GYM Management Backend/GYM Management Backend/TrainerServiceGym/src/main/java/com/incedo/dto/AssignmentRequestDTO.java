package com.incedo.dto;

import jakarta.validation.constraints.NotNull;

public class AssignmentRequestDTO {

    public Long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	@NotNull
    private Long trainerId;

    @NotNull
    private Long memberId;
}
