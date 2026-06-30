package com.armand.portfolio_backend.service.impl;

import com.armand.portfolio_backend.exception.ResourceNotFoundException;
import com.armand.portfolio_backend.model.Project;
import com.armand.portfolio_backend.model.ProjectCategory;
import com.armand.portfolio_backend.repository.ProjectRepository;
import com.armand.portfolio_backend.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projet introuvable avec l'id : " + id));
    }

    @Override
    public List<Project> getProjectsByCategory(ProjectCategory category) {
        return projectRepository.findByCategory(category);
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, Project updatedProject) {
        Project existing = getProjectById(id); // réutilise la méthode, lève l'exception si non trouvé

        existing.setTitle(updatedProject.getTitle());
        existing.setDescription(updatedProject.getDescription());
        existing.setTechStack(updatedProject.getTechStack());
        existing.setGithubUrl(updatedProject.getGithubUrl());
        existing.setDemoUrl(updatedProject.getDemoUrl());
        existing.setCategory(updatedProject.getCategory());

        return projectRepository.save(existing);
    }

    @Override
    public void deleteProject(Long id) {
        Project existing = getProjectById(id);
        projectRepository.delete(existing);
    }
}