package com.incedo.service;

import java.util.List;

import com.incedo.dto.AssignmentRequestDTO;
import com.incedo.dto.AssignmentResponseDTO;
import com.incedo.dto.TrainerRequestDTO;
import com.incedo.dto.TrainerResponseDTO;

public interface TrainerService {

    TrainerResponseDTO createTrainer(TrainerRequestDTO request);

    List<TrainerResponseDTO> getAllTrainers();

    TrainerResponseDTO getTrainerById(Long id);

    TrainerResponseDTO updateTrainer(Long id, TrainerRequestDTO request);

    void deleteTrainer(Long id);

    AssignmentResponseDTO assignTrainerToMember(AssignmentRequestDTO request);

    TrainerResponseDTO getTrainerByMember(Long memberId);
}
