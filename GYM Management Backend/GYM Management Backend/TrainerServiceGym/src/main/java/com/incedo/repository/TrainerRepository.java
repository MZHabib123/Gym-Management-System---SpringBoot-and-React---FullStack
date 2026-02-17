package com.incedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
