package com.gym.repository;

import com.gym.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByMemberId(Long memberId);

    List<Booking> findBySessionId(Long sessionId);

    boolean existsBySessionIdAndMemberId(Long sessionId, Long memberId);

	long countBySessionId(Long id);
}
