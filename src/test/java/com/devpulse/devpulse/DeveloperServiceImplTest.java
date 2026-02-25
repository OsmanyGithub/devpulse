package com.devpulse.devpulse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeveloperServiceImplTest {

    private DeveloperRepository repository;
    private DeveloperServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(DeveloperRepository.class);
        service = new DeveloperServiceImpl(repository);
    }

    @Test
    void testCreateDeveloper() {
        DeveloperRequestDTO request = new DeveloperRequestDTO();
        request.setName("Alice");
        request.setEmail("alice@example.com");
        request.setSkill("Java");

        Developer saved = new Developer("Alice", "alice@example.com", "Java");
        saved.setId(1L);

        when(repository.save(any(Developer.class))).thenReturn(saved);

        DeveloperResponseDTO response = service.createDeveloper(request);

        assertEquals(1L, response.getId());
        assertEquals("Alice", response.getName());
        assertEquals("alice@example.com", response.getEmail());
        assertEquals("Java", response.getSkill());
    }

    @Test
    void testSearchDevelopersBySkill() {
        Developer dev = new Developer("Bob", "bob@example.com", "React");
        dev.setId(2L);

        Page<Developer> page = new PageImpl<>(List.of(dev));

        when(repository.findBySkill("React", Pageable.unpaged())).thenReturn(page);

        Page<DeveloperResponseDTO> result = service.searchDevelopers("React", "", Pageable.unpaged());

        assertEquals(1, result.getTotalElements());
        assertEquals("Bob", result.getContent().get(0).getName());
    }

    @Test
    void testGetDeveloperByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class,
                () -> service.getDeveloperById(99L));

        assertTrue(exception.getMessage().contains("Developer not found"));
    }
}