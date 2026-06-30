package com.armand.portfolio_backend.service;

import com.armand.portfolio_backend.model.Project;
import com.armand.portfolio_backend.model.ProjectCategory;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project getProjectById(Long id);

    List<Project> getProjectsByCategory(ProjectCategory category);

    Project createProject(Project project);

    Project updateProject(Long id, Project updatedProject);

    void deleteProject(Long id);
}