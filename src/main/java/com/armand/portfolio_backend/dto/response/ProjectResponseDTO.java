package com.armand.portfolio_backend.dto.response;

import com.armand.portfolio_backend.model.ProjectCategory;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String techStack;
    private String githubUrl;
    private String demoUrl;
    private ProjectCategory category;
    private LocalDateTime createdAt;
    private List<ProjectImageResponseDTO> images;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTechStack() { return techStack; }
    public void setTechStack(String techStack) { this.techStack = techStack; }
    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public String getDemoUrl() { return demoUrl; }
    public void setDemoUrl(String demoUrl) { this.demoUrl = demoUrl; }
    public ProjectCategory getCategory() { return category; }
    public void setCategory(ProjectCategory category) { this.category = category; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<ProjectImageResponseDTO> getImages() { return images; }
    public void setImages(List<ProjectImageResponseDTO> images) { this.images = images; }
}