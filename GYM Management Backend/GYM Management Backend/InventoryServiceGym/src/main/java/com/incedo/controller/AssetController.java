package com.incedo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.dto.AssetRequestDTO;
import com.incedo.dto.AssetResponseDTO;
import com.incedo.dto.MaintenanceRequestDTO;
import com.incedo.dto.MaintenanceResponseDTO;
import com.incedo.service.AssetService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    public ResponseEntity<AssetResponseDTO> createAsset(
            @Valid @RequestBody AssetRequestDTO dto) {
        return new ResponseEntity<>(assetService.createAsset(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssetResponseDTO>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AssetResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(assetService.updateStatus(id, status));
    }

    @PostMapping("/maintenance")
    public ResponseEntity<MaintenanceResponseDTO> logMaintenance(
            @Valid @RequestBody MaintenanceRequestDTO dto) {
        return new ResponseEntity<>(assetService.logMaintenance(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{assetId}/maintenance")
    public ResponseEntity<List<MaintenanceResponseDTO>> getMaintenanceHistory(
            @PathVariable Long assetId) {
        return ResponseEntity.ok(assetService.getMaintenanceHistory(assetId));
    }
}
