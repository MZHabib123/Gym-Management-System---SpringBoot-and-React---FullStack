package com.gym.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gym.dto.GymClassRequestDTO;
import com.gym.dto.GymClassResponseDTO;
import com.gym.entity.GymClass;
import com.gym.exception.ResourceNotFoundException;
import com.gym.repository.GymClassRepository;

@Service
public class GymClassServiceImpl implements GymClassService {

    private final GymClassRepository gymClassRepository;

    public GymClassServiceImpl(GymClassRepository gymClassRepository) {
        this.gymClassRepository = gymClassRepository;
    }

    @Override
    public GymClassResponseDTO createGymClass(GymClassRequestDTO requestDTO) {

        GymClass gymClass = new GymClass();
        gymClass.setName(requestDTO.getName());
        gymClass.setDescription(requestDTO.getDescription());
        gymClass.setDurationInMinutes(requestDTO.getDurationInMinutes());

        GymClass saved = gymClassRepository.save(gymClass);

        return mapToResponse(saved);
    }

    @Override
    public GymClassResponseDTO getGymClassById(Long id) {

        GymClass gymClass = gymClassRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Gym class not found with id " + id));


        return mapToResponse(gymClass);
    }

    @Override
    public List<GymClassResponseDTO> getAllGymClasses() {

        return gymClassRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GymClassResponseDTO updateGymClass(Long id, GymClassRequestDTO requestDTO) {

        GymClass gymClass = gymClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gym class not found with id " + id));

        gymClass.setName(requestDTO.getName());
        gymClass.setDescription(requestDTO.getDescription());
        gymClass.setDurationInMinutes(requestDTO.getDurationInMinutes());

        GymClass updated = gymClassRepository.save(gymClass);

        return mapToResponse(updated);
    }

    @Override
    public void deleteGymClass(Long id) {

        GymClass gymClass = gymClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gym class not found with id " + id));

        gymClassRepository.delete(gymClass);
    }

    private GymClassResponseDTO mapToResponse(GymClass gymClass) {

        return new GymClassResponseDTO(
                gymClass.getId(),
                gymClass.getName(),
                gymClass.getDescription(),
                gymClass.getDurationInMinutes()
        );
    }
}
