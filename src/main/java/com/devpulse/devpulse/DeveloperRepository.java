package com.devpulse.devpulse;

import com.devpulse.devpulse.Developer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Page<Developer> findBySkill(String skill, Pageable pageable);

    Page<Developer> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Developer> findBySkillAndNameContainingIgnoreCase(String skill, String name, Pageable pageable);
}