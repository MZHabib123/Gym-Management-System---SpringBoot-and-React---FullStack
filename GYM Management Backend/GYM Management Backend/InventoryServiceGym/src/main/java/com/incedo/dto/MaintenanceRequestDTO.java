package com.incedo.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class MaintenanceRequestDTO {

    @NotNull(message = "Asset ID required")
    private Long assetId;

    @NotBlank(message = "Description required")
    private String description;

    @NotNull(message = "Maintenance date required")
    private LocalDate maintenanceDate;

    private Double cost;

    public MaintenanceRequestDTO() {}

    public Long getAssetId() { return assetId; }
    public void setAssetId(Long assetId) { this.assetId = assetId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getMaintenanceDate() { return maintenanceDate; }
    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
}
