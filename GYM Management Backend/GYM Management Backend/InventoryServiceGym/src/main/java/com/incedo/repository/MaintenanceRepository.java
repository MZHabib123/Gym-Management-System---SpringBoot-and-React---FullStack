package com.incedo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.entity.MaintenanceLog;

public interface MaintenanceRepository extends JpaRepository<MaintenanceLog, Long> {
    List<MaintenanceLog> findByAssetId(Long assetId);
}
