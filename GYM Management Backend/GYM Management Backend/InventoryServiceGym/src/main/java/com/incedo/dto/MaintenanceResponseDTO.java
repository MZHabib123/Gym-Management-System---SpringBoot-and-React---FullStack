package com.incedo.dto;

import java.time.LocalDate;

public class MaintenanceResponseDTO {

    private Long id;
    private String description;
    private LocalDate maintenanceDate;
    private Double cost;

    public MaintenanceResponseDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getMaintenanceDate() { return maintenanceDate; }
    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
}
