package com.incedo.service;

import java.util.List;

import com.incedo.dto.AssetRequestDTO;
import com.incedo.dto.AssetResponseDTO;
import com.incedo.dto.MaintenanceRequestDTO;
import com.incedo.dto.MaintenanceResponseDTO;

public interface AssetService {

    AssetResponseDTO createAsset(AssetRequestDTO dto);

    List<AssetResponseDTO> getAllAssets();

    AssetResponseDTO updateStatus(Long id, String status);

    MaintenanceResponseDTO logMaintenance(MaintenanceRequestDTO dto);

    List<MaintenanceResponseDTO> getMaintenanceHistory(Long assetId);
}
