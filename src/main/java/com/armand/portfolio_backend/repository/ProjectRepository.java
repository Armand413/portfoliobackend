package com.armand.portfolio_backend.repository;

import com.armand.portfolio_backend.model.Project;
import com.armand.portfolio_backend.model.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Récupère tous les projets d'une catégorie donnée
    List<Project> findByCategory(ProjectCategory category);

    // Recherche les projets dont le titre contient un mot-clé (insensible à la casse)
    List<Project> findByTitleContainingIgnoreCase(String keyword);

    // Récupère les projets triés par date de création, du plus récent au plus ancien
    List<Project> findAllByOrderByCreatedAtDesc();
}