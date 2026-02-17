package com.gym.repository;

import com.gym.entity.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {

    List<ClassSession> findBySessionDate(LocalDate sessionDate);

    List<ClassSession> findByTrainerId(Long trainerId);
}
