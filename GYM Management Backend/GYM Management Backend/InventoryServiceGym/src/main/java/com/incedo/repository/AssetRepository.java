package com.incedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
