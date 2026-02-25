package com.devpulse.devpulse.ai;

import com.devpulse.devpulse.DeveloperRequestDTO;
import com.devpulse.devpulse.DeveloperResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AIServiceTest {

    private AIService aiService;

    @BeforeEach
    void setUp() {
        aiService = new AIService();
    }

    @Test
    void testAnalyzeDeveloperRequest_withInvalidData() {
        DeveloperRequestDTO request = new DeveloperRequestDTO();
        request.setName("");
        request.setEmail("wrongemail");
        request.setSkill("J");

        List<String> suggestions = aiService.analyzeDeveloperRequest(request);

        assertEquals(3, suggestions.size());
        assertTrue(suggestions.contains("Name is empty. Consider providing a valid name."));
        assertTrue(suggestions.contains("Email format seems invalid."));
        assertTrue(suggestions.contains("Skill name is too short. Consider using full skill name."));
    }

    @Test
    void testAnalyzeDeveloperRequest_withValidData() {
        DeveloperRequestDTO request = new DeveloperRequestDTO();
        request.setName("Alice");
        request.setEmail("alice@example.com");
        request.setSkill("Java");

        List<String> suggestions = aiService.analyzeDeveloperRequest(request);

        assertTrue(suggestions.isEmpty());
    }

    @Test
    void testAutoFillDeveloper_withMissingFields() {
        DeveloperRequestDTO request = new DeveloperRequestDTO();

        DeveloperResponseDTO response = aiService.autoFillDeveloper(request);

        assertEquals("John Doe", response.getName());
        assertEquals("test@example.com", response.getEmail());
        assertEquals("Java", response.getSkill());
    }
}