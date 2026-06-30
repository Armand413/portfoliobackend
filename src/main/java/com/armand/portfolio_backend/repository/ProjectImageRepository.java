package com.armand.portfolio_backend.repository;

import com.armand.portfolio_backend.model.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {

    // Récupère toutes les images d'un projet, triées par ordre d'affichage
    List<ProjectImage> findByProjectIdOrderByDisplayOrderAsc(Long projectId);
}