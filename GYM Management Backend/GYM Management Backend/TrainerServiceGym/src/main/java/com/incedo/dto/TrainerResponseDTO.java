package com.incedo.dto;

import com.incedo.entity.TrainerStatus;

public class TrainerResponseDTO {

    private Long id;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public int getExperienceYears() {
		return experienceYears;
	}
	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public TrainerStatus getStatus() {
		return status;
	}
	public void setStatus(TrainerStatus status) {
		this.status = status;
	}
	private String name;
    private String specialization;
    private int experienceYears;
    private String email;
    private String phone;
    private TrainerStatus status;
}
