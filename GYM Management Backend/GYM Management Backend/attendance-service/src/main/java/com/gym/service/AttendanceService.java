package com.gym.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.client.MemberClient;
import com.gym.dto.AttendanceRequestDTO;
import com.gym.dto.AttendanceResponseDTO;
import com.gym.entity.AttendanceLog;
import com.gym.exception.AttendanceException;
import com.gym.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    
    @Autowired
    private MemberClient memberClient;


    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Check-In
    public AttendanceResponseDTO checkIn(AttendanceRequestDTO request) {

        Long memberId = request.getMemberId();
     // 🔥 1. Validate member first
        if (!memberClient.memberExists(memberId)) {
            throw new RuntimeException("Member not found");
        }

        // Prevent double check-in
        attendanceRepository.findByMemberIdAndCheckOutTimeIsNull(memberId)
                .ifPresent(record -> {
                	throw new AttendanceException("Member already checked in.");
                });

        AttendanceLog log = new AttendanceLog(memberId, LocalDateTime.now());
        AttendanceLog saved = attendanceRepository.save(log);

        return convertToDTO(saved);
    }

    // Check-Out
    public AttendanceResponseDTO checkOut(AttendanceRequestDTO request) {

        Long memberId = request.getMemberId();

        AttendanceLog activeLog = attendanceRepository
                .findByMemberIdAndCheckOutTimeIsNull(memberId)
                .orElseThrow(() -> new AttendanceException("No active check-in found."));


        activeLog.setCheckOutTime(LocalDateTime.now());
        AttendanceLog updated = attendanceRepository.save(activeLog);

        return convertToDTO(updated);
    }

    // Get Attendance History
    public List<AttendanceResponseDTO> getAttendanceByMember(Long memberId) {

        List<AttendanceLog> logs = attendanceRepository
                .findByMemberIdOrderByCheckInTimeDesc(memberId);

        return logs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convert Entity → DTO
    private AttendanceResponseDTO convertToDTO(AttendanceLog log) {
        return new AttendanceResponseDTO(
                log.getId(),
                log.getMemberId(),
                log.getCheckInTime(),
                log.getCheckOutTime()
        );
    }
}
