package com.incedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
