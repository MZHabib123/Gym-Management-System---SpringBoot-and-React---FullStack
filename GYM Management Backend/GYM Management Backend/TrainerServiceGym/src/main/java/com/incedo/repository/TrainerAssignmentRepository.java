package com.incedo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.entity.AssignmentStatus;
import com.incedo.entity.TrainerAssignment;

public interface TrainerAssignmentRepository 
        extends JpaRepository<TrainerAssignment, Long> {

    boolean existsByTrainerIdAndMemberIdAndStatus(
            Long trainerId,
            Long memberId,
            AssignmentStatus status
    );

    Optional<TrainerAssignment> findByMemberIdAndStatus(
            Long memberId,
            AssignmentStatus status
    );
}
