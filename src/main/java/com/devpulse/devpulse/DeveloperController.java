package com.devpulse.devpulse;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    public DeveloperResponseDTO create(
            @Valid @RequestBody DeveloperRequestDTO request) {

        return developerService.createDeveloper(request);
    }

    @GetMapping("/{id}")
    public DeveloperResponseDTO getById(@PathVariable Long id) {
        return developerService.getDeveloperById(id);
    }

    @GetMapping
    public Page<DeveloperResponseDTO> getAll(Pageable pageable) {
        return developerService.searchDevelopers("", "", pageable);
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