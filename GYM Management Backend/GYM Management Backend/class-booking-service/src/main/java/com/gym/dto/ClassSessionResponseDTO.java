package com.gym.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClassSessionResponseDTO {

    private Long id;
    private Long gymClassId;
    private Long trainerId;
    private LocalDate sessionDate;
    private LocalTime startTime;
    private Integer capacity;

    public ClassSessionResponseDTO() {
    }

    public ClassSessionResponseDTO(Long id, Long gymClassId, Long trainerId,
                                   LocalDate sessionDate, LocalTime startTime, Integer capacity) {
        this.id = id;
        this.gymClassId = gymClassId;
        this.trainerId = trainerId;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
