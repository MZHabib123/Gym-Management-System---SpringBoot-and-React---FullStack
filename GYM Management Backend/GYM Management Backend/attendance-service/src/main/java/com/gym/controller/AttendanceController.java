package com.gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.dto.AttendanceRequestDTO;
import com.gym.dto.AttendanceResponseDTO;
import com.gym.service.AttendanceService;

@RestController
@CrossOrigin("*")
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // 🔹 Check-In API
    @PostMapping("/check-in")
    public ResponseEntity<AttendanceResponseDTO> checkIn(
            @RequestBody AttendanceRequestDTO request) {

        AttendanceResponseDTO response = attendanceService.checkIn(request);
        return ResponseEntity.ok(response);
    }

    // 🔹 Check-Out API
    @PostMapping("/check-out")
    public ResponseEntity<AttendanceResponseDTO> checkOut(
            @RequestBody AttendanceRequestDTO request) {

        AttendanceResponseDTO response = attendanceService.checkOut(request);
        return ResponseEntity.ok(response);
    }

    // 🔹 Get Attendance History
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendance(
            @PathVariable Long memberId) {

        List<AttendanceResponseDTO> response =
                attendanceService.getAttendanceByMember(memberId);

        return ResponseEntity.ok(response);
    }
}
