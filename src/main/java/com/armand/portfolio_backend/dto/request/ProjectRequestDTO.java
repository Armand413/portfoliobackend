package com.armand.portfolio_backend.dto.request;

import com.armand.portfolio_backend.model.ProjectCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectRequestDTO {

    @NotBlank(message = "Le titre est requis")
    @Size(max = 150, message = "Le titre ne doit pas dépasser 150 caractères")
    private String title;

    @NotBlank(message = "La description est requise")
    private String description;

    private String techStack;
    private String githubUrl;
    private String demoUrl;
    private ProjectCategory category;

    // Getters et setters
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
}