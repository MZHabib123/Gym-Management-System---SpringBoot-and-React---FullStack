package com.incedo.dto;

import com.incedo.entity.AssignmentStatus;

public class AssignmentResponseDTO {

    public Long getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}
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
	public AssignmentStatus getStatus() {
		return status;
	}
	public void setStatus(AssignmentStatus status) {
		this.status = status;
	}
	private Long assignmentId;
    private Long trainerId;
    private Long memberId;
    private AssignmentStatus status;
}
