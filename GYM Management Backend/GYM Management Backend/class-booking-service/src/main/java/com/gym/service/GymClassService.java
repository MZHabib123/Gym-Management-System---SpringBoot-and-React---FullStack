package com.gym.service;

import com.gym.dto.GymClassRequestDTO;
import com.gym.dto.GymClassResponseDTO;

import java.util.List;

public interface GymClassService {

    GymClassResponseDTO createGymClass(GymClassRequestDTO requestDTO);

    GymClassResponseDTO getGymClassById(Long id);

    List<GymClassResponseDTO> getAllGymClasses();

    GymClassResponseDTO updateGymClass(Long id, GymClassRequestDTO requestDTO);

    void deleteGymClass(Long id);
}
