package com.gym.dto;

public class GymClassResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Integer durationInMinutes;

    public GymClassResponseDTO() {
    }

    public GymClassResponseDTO(Long id, String name, String description, Integer durationInMinutes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
