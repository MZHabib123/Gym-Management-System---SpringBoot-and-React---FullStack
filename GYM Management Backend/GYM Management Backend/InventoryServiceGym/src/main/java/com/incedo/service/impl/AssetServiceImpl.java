package com.incedo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incedo.dto.AssetRequestDTO;
import com.incedo.dto.AssetResponseDTO;
import com.incedo.dto.MaintenanceRequestDTO;
import com.incedo.dto.MaintenanceResponseDTO;
import com.incedo.entity.Asset;
import com.incedo.entity.MaintenanceLog;
import com.incedo.exception.ResourceNotFoundException;
import com.incedo.repository.AssetRepository;
import com.incedo.repository.MaintenanceRepository;
import com.incedo.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final MaintenanceRepository maintenanceRepository;

    public AssetServiceImpl(AssetRepository assetRepository,
                            MaintenanceRepository maintenanceRepository) {
        this.assetRepository = assetRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public AssetResponseDTO createAsset(AssetRequestDTO dto) {

        Asset asset = new Asset();
        asset.setName(dto.getName());
        asset.setCategory(dto.getCategory());
        asset.setQuantity(dto.getQuantity());
        asset.setStatus(dto.getStatus());
        asset.setPurchaseDate(dto.getPurchaseDate());

        Asset saved = assetRepository.save(asset);

        return mapToAssetResponse(saved);
    }

    @Override
    public List<AssetResponseDTO> getAllAssets() {
        return assetRepository.findAll()
                .stream()
                .map(this::mapToAssetResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AssetResponseDTO updateStatus(Long id, String status) {

        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        asset.setStatus(status);

        return mapToAssetResponse(assetRepository.save(asset));
    }

    @Override
    public MaintenanceResponseDTO logMaintenance(MaintenanceRequestDTO dto) {

        Asset asset = assetRepository.findById(dto.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        MaintenanceLog log = new MaintenanceLog();
        log.setDescription(dto.getDescription());
        log.setMaintenanceDate(dto.getMaintenanceDate());
        log.setCost(dto.getCost());
        log.setAsset(asset);

        MaintenanceLog saved = maintenanceRepository.save(log);

        return mapToMaintenanceResponse(saved);
    }

    @Override
    public List<MaintenanceResponseDTO> getMaintenanceHistory(Long assetId) {

        return maintenanceRepository.findByAssetId(assetId)
                .stream()
                .map(this::mapToMaintenanceResponse)
                .collect(Collectors.toList());
    }

    private AssetResponseDTO mapToAssetResponse(Asset asset) {
        AssetResponseDTO dto = new AssetResponseDTO();
        dto.setId(asset.getId());
        dto.setName(asset.getName());
        dto.setCategory(asset.getCategory());
        dto.setQuantity(asset.getQuantity());
        dto.setStatus(asset.getStatus());
        dto.setPurchaseDate(asset.getPurchaseDate());
        return dto;
    }

    private MaintenanceResponseDTO mapToMaintenanceResponse(MaintenanceLog log) {
        MaintenanceResponseDTO dto = new MaintenanceResponseDTO();
        dto.setId(log.getId());
        dto.setDescription(log.getDescription());
        dto.setMaintenanceDate(log.getMaintenanceDate());
        dto.setCost(log.getCost());
        return dto;
    }
}
