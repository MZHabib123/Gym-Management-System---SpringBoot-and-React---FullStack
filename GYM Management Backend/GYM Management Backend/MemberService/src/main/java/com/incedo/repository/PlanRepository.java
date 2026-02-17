package com.incedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
