package com.gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.dto.ClassSessionRequestDTO;
import com.gym.dto.ClassSessionResponseDTO;
import com.gym.service.ClassSessionService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/sessions")
public class ClassSessionController {

    private final ClassSessionService sessionService;

    public ClassSessionController(ClassSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<ClassSessionResponseDTO> createSession(
            @Valid @RequestBody ClassSessionRequestDTO requestDTO) {

        return ResponseEntity.ok(sessionService.createSession(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassSessionResponseDTO> getSessionById(@PathVariable Long id) {

        return ResponseEntity.ok(sessionService.getSessionById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassSessionResponseDTO>> getAllSessions() {

        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {

        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}
