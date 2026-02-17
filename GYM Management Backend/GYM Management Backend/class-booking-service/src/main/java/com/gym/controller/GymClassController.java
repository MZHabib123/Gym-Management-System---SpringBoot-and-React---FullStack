package com.gym.controller;

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

import com.gym.dto.GymClassRequestDTO;
import com.gym.dto.GymClassResponseDTO;
import com.gym.service.GymClassService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/classes")
public class GymClassController {

    private final GymClassService gymClassService;

    public GymClassController(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    @PostMapping
    public ResponseEntity<GymClassResponseDTO> createClass(
            @Valid @RequestBody GymClassRequestDTO requestDTO) {

        return ResponseEntity.ok(gymClassService.createGymClass(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymClassResponseDTO> getClassById(@PathVariable Long id) {

        return ResponseEntity.ok(gymClassService.getGymClassById(id));
    }

    @GetMapping
    public ResponseEntity<List<GymClassResponseDTO>> getAllClasses() {

        return ResponseEntity.ok(gymClassService.getAllGymClasses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GymClassResponseDTO> updateClass(
            @PathVariable Long id,
            @Valid @RequestBody GymClassRequestDTO requestDTO) {

        return ResponseEntity.ok(gymClassService.updateGymClass(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {

        gymClassService.deleteGymClass(id);
        return ResponseEntity.noContent().build();
    }
}
