package com.incedo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incedo.dto.AssignmentRequestDTO;
import com.incedo.dto.AssignmentResponseDTO;
import com.incedo.dto.MemberDTO;
import com.incedo.dto.TrainerRequestDTO;
import com.incedo.dto.TrainerResponseDTO;
import com.incedo.entity.AssignmentStatus;
import com.incedo.entity.Trainer;
import com.incedo.entity.TrainerAssignment;
import com.incedo.entity.TrainerStatus;
import com.incedo.exception.AssignmentException;
import com.incedo.exception.TrainerNotFoundException;
import com.incedo.repository.TrainerAssignmentRepository;
import com.incedo.repository.TrainerRepository;
import com.incedo.service.MemberClientService;
import com.incedo.service.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerAssignmentRepository assignmentRepository;
    private final MemberClientService memberClientService;
    public TrainerServiceImpl(TrainerRepository trainerRepository,
                              TrainerAssignmentRepository assignmentRepository,
                              MemberClientService memberClientService) {
        this.trainerRepository = trainerRepository;
        this.assignmentRepository = assignmentRepository;
        this.memberClientService = memberClientService;
    }

   
    // ===============================
    // CREATE TRAINER
    // ===============================
    @Override
    public TrainerResponseDTO createTrainer(TrainerRequestDTO request) {

        Trainer trainer = new Trainer();
        trainer.setName(request.getName());
        trainer.setSpecialization(request.getSpecialization());
        trainer.setExperienceYears(request.getExperienceYears());
        trainer.setEmail(request.getEmail());
        trainer.setPhone(request.getPhone());

        Trainer savedTrainer = trainerRepository.save(trainer);

        return mapToResponse(savedTrainer);
    }

    // ===============================
    // GET ALL TRAINERS
    // ===============================
    @Override
    public List<TrainerResponseDTO> getAllTrainers() {
        return trainerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ===============================
    // GET TRAINER BY ID
    // ===============================
    @Override
    public TrainerResponseDTO getTrainerById(Long id) {

        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() ->
                        new TrainerNotFoundException("Trainer not found with ID: " + id));

        return mapToResponse(trainer);
    }

    // ===============================
    // UPDATE TRAINER
    // ===============================
    @Override
    public TrainerResponseDTO updateTrainer(Long id, TrainerRequestDTO request) {

        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() ->
                        new TrainerNotFoundException("Trainer not found with ID: " + id));

        trainer.setName(request.getName());
        trainer.setSpecialization(request.getSpecialization());
        trainer.setExperienceYears(request.getExperienceYears());
        trainer.setEmail(request.getEmail());
        trainer.setPhone(request.getPhone());

        Trainer updated = trainerRepository.save(trainer);

        return mapToResponse(updated);
    }

    // ===============================
    // DELETE TRAINER (SOFT DELETE)
    // ===============================
    @Override
    public void deleteTrainer(Long id) {

        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() ->
                        new TrainerNotFoundException("Trainer not found with ID: " + id));

        trainer.setStatus(TrainerStatus.INACTIVE);
        trainerRepository.save(trainer);
    }

    // ===============================
    // ASSIGN TRAINER TO MEMBER
    // ===============================
    @Override
    public AssignmentResponseDTO assignTrainerToMember(AssignmentRequestDTO request) {

        // 1️⃣ Validate trainer
        Trainer trainer = trainerRepository.findById(request.getTrainerId())
                .orElseThrow(() ->
                        new TrainerNotFoundException("Trainer not found"));

        if (trainer.getStatus() == TrainerStatus.INACTIVE) {
            throw new AssignmentException("Cannot assign inactive trainer");
        }

        // 2️⃣ Validate member exists via Member Service
        MemberDTO member = memberClientService.getMemberById(request.getMemberId());
        if (member == null) {
            throw new AssignmentException("Member not found with ID: " + request.getMemberId());
        }

        // 3️⃣ Prevent duplicate assignment
        boolean exists = assignmentRepository
                .existsByTrainerIdAndMemberIdAndStatus(
                        trainer.getId(),
                        request.getMemberId(),
                        AssignmentStatus.ACTIVE
                );

        if (exists) {
            throw new AssignmentException("Trainer already assigned to this member");
        }

        // 4️⃣ Save assignment
        TrainerAssignment assignment = new TrainerAssignment();
        assignment.setTrainer(trainer);
        assignment.setMemberId(request.getMemberId());

        TrainerAssignment saved = assignmentRepository.save(assignment);

        // 5️⃣ Prepare response
        AssignmentResponseDTO response = new AssignmentResponseDTO();
        response.setAssignmentId(saved.getId());
        response.setTrainerId(trainer.getId());
        response.setMemberId(saved.getMemberId());
        response.setStatus(saved.getStatus());

        return response;
    }

    // ===============================
    // GET TRAINER BY MEMBER
    // ===============================
    @Override
    public TrainerResponseDTO getTrainerByMember(Long memberId) {

        TrainerAssignment assignment = assignmentRepository
                .findByMemberIdAndStatus(memberId, AssignmentStatus.ACTIVE)
                .orElseThrow(() ->
                        new AssignmentException("No active trainer for this member"));

        return mapToResponse(assignment.getTrainer());
    }

    // ===============================
    // MAPPER METHOD
    // ===============================
    private TrainerResponseDTO mapToResponse(Trainer trainer) {

        TrainerResponseDTO dto = new TrainerResponseDTO();
        dto.setId(trainer.getId());
        dto.setName(trainer.getName());
        dto.setSpecialization(trainer.getSpecialization());
        dto.setExperienceYears(trainer.getExperienceYears());
        dto.setEmail(trainer.getEmail());
        dto.setPhone(trainer.getPhone());
        dto.setStatus(trainer.getStatus());

        return dto;
    }
}
