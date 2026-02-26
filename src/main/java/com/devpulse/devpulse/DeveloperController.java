package com.devpulse.devpulse;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devpulse.devpulse.ai.AIService;

import java.util.List;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperService developerService;
    private final AIService aiService;

    public DeveloperController(DeveloperService developerService, AIService aiService) {
        this.developerService = developerService;
        this.aiService = aiService;
    }

    @PostMapping
    public DeveloperResponseDTO create(
            @Valid @RequestBody DeveloperRequestDTO request) {

        return developerService.createDeveloper(request);
    }

    @PostMapping("/ai-check")
    public ResponseEntity<List<String>> aiCheckDeveloper(@RequestBody DeveloperRequestDTO request){
        List<String> suggestions = aiService.analyzeDeveloperRequest(request);
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/{id}")
    public DeveloperResponseDTO getById(@PathVariable Long id) {
        return developerService.getDeveloperById(id);
    }

    @GetMapping
    public List<DeveloperResponseDTO> getAll(Pageable pageable) {
        return developerService.searchDevelopers("", "", pageable).getContent();
    }

    @GetMapping("/search")
    public Page<DeveloperResponseDTO> search(
            @RequestParam(defaultValue = "") String skill,
            @RequestParam(defaultValue = "") String name,
            Pageable pageable) {

        return developerService.searchDevelopers(skill, name, pageable);
    }

    @PutMapping("/{id}")
    public DeveloperResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody DeveloperRequestDTO request) {

        return developerService.updateDeveloper(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        developerService.deleteDeveloper(id);
    }
}