package com.incedo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.dto.AssignmentRequestDTO;
import com.incedo.dto.AssignmentResponseDTO;
import com.incedo.dto.TrainerRequestDTO;
import com.incedo.dto.TrainerResponseDTO;
import com.incedo.service.TrainerService;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@CrossOrigin("*")
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    // ===============================
    // CREATE TRAINER
    // ===============================
    @PostMapping
    public ResponseEntity<TrainerResponseDTO> createTrainer(
            @Valid @RequestBody TrainerRequestDTO request) {

        TrainerResponseDTO response = trainerService.createTrainer(request);
        return ResponseEntity.ok(response);
    }

    // ===============================
    // GET ALL TRAINERS
    // ===============================
    @GetMapping
    public ResponseEntity<List<TrainerResponseDTO>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }
    
    // ===============================
    // GET TRAINER BY ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<TrainerResponseDTO> getTrainerById(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    // ===============================
    // UPDATE TRAINER
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<TrainerResponseDTO> updateTrainer(
            @PathVariable Long id,
            @Valid @RequestBody TrainerRequestDTO request) {

        return ResponseEntity.ok(trainerService.updateTrainer(id, request));
    }

    // ===============================
    // DELETE TRAINER (SOFT DELETE)
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.ok("Trainer deactivated successfully");
    }

    // ===============================
    // ASSIGN TRAINER TO MEMBER
    // ===============================
    @PostMapping("/assign")
    public ResponseEntity<AssignmentResponseDTO> assignTrainer(
            @Valid @RequestBody AssignmentRequestDTO request) {

        return ResponseEntity.ok(trainerService.assignTrainerToMember(request));
    }

    // ===============================
    // GET TRAINER BY MEMBER
    // ===============================
    @GetMapping("/member/{memberId}")
    public ResponseEntity<TrainerResponseDTO> getTrainerByMember(
            @PathVariable Long memberId) {

        return ResponseEntity.ok(trainerService.getTrainerByMember(memberId));
    }
}
