package com.gym.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClassSessionRequestDTO {

    @NotNull(message = "Gym class id is required")
    private Long gymClassId;

    @NotNull(message = "Trainer id is required")
    private Long trainerId;

    @NotNull(message = "Session date is required")
    private LocalDate sessionDate;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be positive")
    private Integer capacity;

    public ClassSessionRequestDTO() {
    }

    public ClassSessionRequestDTO(Long gymClassId, Long trainerId,
                                  LocalDate sessionDate, LocalTime startTime, Integer capacity) {
        this.gymClassId = gymClassId;
        this.trainerId = trainerId;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.capacity = capacity;
    }

    public Long getGymClassId() {
        return gymClassId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setGymClassId(Long gymClassId) {
        this.gymClassId = gymClassId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
