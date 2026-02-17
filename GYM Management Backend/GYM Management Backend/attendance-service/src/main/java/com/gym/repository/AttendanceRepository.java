package com.gym.repository;

import com.gym.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceLog, Long> {

    // Find active check-in (checkOutTime is null)
    Optional<AttendanceLog> findByMemberIdAndCheckOutTimeIsNull(Long memberId);

    // Get all attendance records of a member
    List<AttendanceLog> findByMemberIdOrderByCheckInTimeDesc(Long memberId);
}
