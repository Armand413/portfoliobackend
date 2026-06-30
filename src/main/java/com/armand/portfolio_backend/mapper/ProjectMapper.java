package com.armand.portfolio_backend.mapper;

import com.armand.portfolio_backend.dto.request.ProjectRequestDTO;
import com.armand.portfolio_backend.dto.response.ProjectImageResponseDTO;
import com.armand.portfolio_backend.dto.response.ProjectResponseDTO;
import com.armand.portfolio_backend.model.Project;
import com.armand.portfolio_backend.model.ProjectImage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    public ProjectResponseDTO toDTO(Project project) {
        ProjectResponseDTO dto = new ProjectResponseDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setTechStack(project.getTechStack());
        dto.setGithubUrl(project.getGithubUrl());
        dto.setDemoUrl(project.getDemoUrl());
        dto.setCategory(project.getCategory());
        dto.setCreatedAt(project.getCreatedAt());

        List<ProjectImageResponseDTO> images = project.getImages().stream()
                .map(this::toImageDTO)
                .collect(Collectors.toList());
        dto.setImages(images);

        return dto;
    }

    private ProjectImageResponseDTO toImageDTO(ProjectImage image) {
        return new ProjectImageResponseDTO(image.getId(), image.getFileName(), image.getDisplayOrder());
    }

    public Project toEntity(ProjectRequestDTO dto) {
        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setTechStack(dto.getTechStack());
        project.setGithubUrl(dto.getGithubUrl());
        project.setDemoUrl(dto.getDemoUrl());
        project.setCategory(dto.getCategory());
        return project;
    }
}