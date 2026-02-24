package com.devpulse.devpulse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeveloperService {

    DeveloperResponseDTO createDeveloper(DeveloperRequestDTO request);

    DeveloperResponseDTO getDeveloperById(Long id);

    Page<DeveloperResponseDTO> searchDevelopers(String skill, String name, Pageable pageable);

    DeveloperResponseDTO updateDeveloper(Long id, DeveloperRequestDTO request);

    void deleteDeveloper(Long id);
}