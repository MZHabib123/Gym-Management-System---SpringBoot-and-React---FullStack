package com.gym.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gym.dto.ClassSessionRequestDTO;
import com.gym.dto.ClassSessionResponseDTO;
import com.gym.dto.TrainerDTO;
import com.gym.entity.ClassSession;
import com.gym.exception.ResourceNotFoundException;
import com.gym.repository.ClassSessionRepository;
import com.gym.repository.GymClassRepository;

import reactor.core.publisher.Mono;

@Service
public class ClassSessionServiceImpl implements ClassSessionService {

    private final ClassSessionRepository sessionRepository;
    private final GymClassRepository gymClassRepository;

    private final WebClient webClient;

    public ClassSessionServiceImpl(ClassSessionRepository sessionRepository,
                                    GymClassRepository gymClassRepository,
                                    WebClient webClient) {
        this.sessionRepository = sessionRepository;
        this.gymClassRepository = gymClassRepository;
        this.webClient = webClient;
    }

    @Override
    public ClassSessionResponseDTO createSession(ClassSessionRequestDTO requestDTO) {

        // ✅ Validate GymClass
        gymClassRepository.findById(requestDTO.getGymClassId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Gym class not found with id " + requestDTO.getGymClassId()));

        // ✅ Validate Trainer via WebClient
        TrainerDTO trainer = webClient.get()
                .uri("http://localhost:8084/trainers/" + requestDTO.getTrainerId())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> Mono.error(new ResourceNotFoundException(
                                "Trainer not found with id " + requestDTO.getTrainerId())))
                .bodyToMono(TrainerDTO.class)
                .block();


        if (trainer == null) {
            throw new ResourceNotFoundException(
                    "Trainer not found with id " + requestDTO.getTrainerId());
        }

        // ✅ Save session
        ClassSession session = new ClassSession();
        session.setGymClassId(requestDTO.getGymClassId());
        session.setTrainerId(requestDTO.getTrainerId());
        session.setSessionDate(requestDTO.getSessionDate());
        session.setStartTime(requestDTO.getStartTime());
        session.setCapacity(requestDTO.getCapacity());

        ClassSession saved = sessionRepository.save(session);

        return mapToResponse(saved);
    }


    @Override
    public ClassSessionResponseDTO getSessionById(Long id) {

        ClassSession session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session not found with id " + id));

        return mapToResponse(session);
    }

    @Override
    public List<ClassSessionResponseDTO> getAllSessions() {

        return sessionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSession(Long id) {

        ClassSession session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session not found with id " + id));

        sessionRepository.delete(session);
    }

    private ClassSessionResponseDTO mapToResponse(ClassSession session) {

        return new ClassSessionResponseDTO(
                session.getId(),
                session.getGymClassId(),
                session.getTrainerId(),
                session.getSessionDate(),
                session.getStartTime(),
                session.getCapacity()
        );
    }
}
