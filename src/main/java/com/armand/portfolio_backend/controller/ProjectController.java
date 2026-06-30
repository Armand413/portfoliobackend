package com.armand.portfolio_backend.controller;

import com.armand.portfolio_backend.dto.request.ProjectRequestDTO;
import com.armand.portfolio_backend.dto.response.ProjectResponseDTO;
import com.armand.portfolio_backend.mapper.ProjectMapper;
import com.armand.portfolio_backend.model.Project;
import com.armand.portfolio_backend.model.ProjectCategory;
import com.armand.portfolio_backend.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> result = projectService.getAllProjects().stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(projectMapper.toDTO(project));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProjectResponseDTO>> getProjectsByCategory(@PathVariable ProjectCategory category) {
        List<ProjectResponseDTO> result = projectService.getProjectsByCategory(category).stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO requestDTO) {
        Project project = projectMapper.toEntity(requestDTO);
        Project created = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id,
                                                            @Valid @RequestBody ProjectRequestDTO requestDTO) {
        Project project = projectMapper.toEntity(requestDTO);
        Project updated = projectService.updateProject(id, project);
        return ResponseEntity.ok(projectMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}