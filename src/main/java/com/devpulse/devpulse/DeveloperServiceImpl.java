package com.devpulse.devpulse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public DeveloperResponseDTO createDeveloper(DeveloperRequestDTO request) {

        Developer developer = new Developer();
        developer.setName(request.getName());
        developer.setEmail(request.getEmail());
        developer.setSkill(request.getSkill());

        Developer saved = developerRepository.save(developer);

        return mapToResponse(saved);
    }

    @Override
    public DeveloperResponseDTO getDeveloperById(Long id) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Developer not found with id: " + id));

        return mapToResponse(developer);
    }

    @Override
    public Page<DeveloperResponseDTO> searchDevelopers(String skill, String name, Pageable pageable) {

        Page<Developer> page;

        if (!skill.isBlank() && !name.isBlank()) {
            page = developerRepository.findBySkillAndNameContainingIgnoreCase(skill, name, pageable);
        } else if (!skill.isBlank()) {
            page = developerRepository.findBySkill(skill, pageable);
        } else if (!name.isBlank()) {
            page = developerRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            page = developerRepository.findAll(pageable);
        }

        return page.map(this::mapToResponse);
    }

    @Override
    public DeveloperResponseDTO updateDeveloper(Long id, DeveloperRequestDTO request) {

        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Developer not found with id: " + id));

        developer.setName(request.getName());
        developer.setEmail(request.getEmail());
        developer.setSkill(request.getSkill());

        Developer updated = developerRepository.save(developer);

        return mapToResponse(updated);
    }

    @Override
    public void deleteDeveloper(Long id) {

        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Developer not found with id: " + id));

        developerRepository.delete(developer);
    }

    private DeveloperResponseDTO mapToResponse(Developer developer) {
        return new DeveloperResponseDTO(
                developer.getId(),
                developer.getName(),
                developer.getEmail(),
                developer.getSkill()
        );
    }
}