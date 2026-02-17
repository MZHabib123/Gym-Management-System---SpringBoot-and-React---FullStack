package com.incedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.entity.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
