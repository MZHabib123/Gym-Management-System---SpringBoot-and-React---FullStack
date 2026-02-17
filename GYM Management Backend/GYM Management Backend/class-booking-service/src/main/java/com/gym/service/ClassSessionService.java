package com.gym.service;

import com.gym.dto.ClassSessionRequestDTO;
import com.gym.dto.ClassSessionResponseDTO;

import java.util.List;

public interface ClassSessionService {

    ClassSessionResponseDTO createSession(ClassSessionRequestDTO requestDTO);

    ClassSessionResponseDTO getSessionById(Long id);

    List<ClassSessionResponseDTO> getAllSessions();

    void deleteSession(Long id);
}
