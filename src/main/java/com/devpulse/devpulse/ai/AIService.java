package com.devpulse.devpulse.ai;

import com.devpulse.devpulse.DeveloperRequestDTO;
import com.devpulse.devpulse.DeveloperResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIService {

    /**
     * Suggest improvements or checks for a developer request.
     * Example: validate skill names, email format (extra check), or missing fields.
     */
    public List<String> analyzeDeveloperRequest(DeveloperRequestDTO request) {
        List<String> suggestions = new ArrayList<>();

        if (request.getName() == null || request.getName().isBlank()) {
            suggestions.add("Name is empty. Consider providing a valid name.");
        }

        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            suggestions.add("Email format seems invalid.");
        }

        if (request.getSkill() == null || request.getSkill().isBlank()) {
            suggestions.add("Skill is missing. Add at least one skill.");
        }

        if (request.getSkill() != null && request.getSkill().length() < 3) {
            suggestions.add("Skill name is too short. Consider using full skill name.");
        }

        return suggestions;
    }

    /**
     * Optional: A method to simulate AI-generated automatic response for testing.
     */
    public DeveloperResponseDTO autoFillDeveloper(DeveloperRequestDTO request) {
        String name = (request.getName() == null || request.getName().isBlank())
                ? "John Doe" : request.getName();
        String email = (request.getEmail() == null || request.getEmail().isBlank())
                ? "test@example.com" : request.getEmail();
        String skill = (request.getSkill() == null || request.getSkill().isBlank())
                ? "Java" : request.getSkill();

        return new DeveloperResponseDTO(null, name, email, skill);
    }
}