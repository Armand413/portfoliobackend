package com.armand.portfolio_backend.service.impl;

import com.armand.portfolio_backend.exception.ResourceNotFoundException;
import com.armand.portfolio_backend.model.Project;
import com.armand.portfolio_backend.model.ProjectImage;
import com.armand.portfolio_backend.repository.ProjectImageRepository;
import com.armand.portfolio_backend.repository.ProjectRepository;
import com.armand.portfolio_backend.service.ProjectImageService;
import com.armand.portfolio_backend.util.FileStorageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProjectImageServiceImpl implements ProjectImageService {

    private final ProjectImageRepository projectImageRepository;
    private final ProjectRepository projectRepository;
    private final FileStorageUtils fileStorageUtils;

    public ProjectImageServiceImpl(ProjectImageRepository projectImageRepository,
                                   ProjectRepository projectRepository,
                                   FileStorageUtils fileStorageUtils) {
        this.projectImageRepository = projectImageRepository;
        this.projectRepository = projectRepository;
        this.fileStorageUtils = fileStorageUtils;
    }

    @Override
    public List<ProjectImage> getImagesByProjectId(Long projectId) {
        return projectImageRepository.findByProjectIdOrderByDisplayOrderAsc(projectId);
    }

    @Override
    public ProjectImage addImageToProject(Long projectId, MultipartFile file) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projet introuvable avec l'id : " + projectId));

        String fileName = fileStorageUtils.storeFile(file);

        ProjectImage image = new ProjectImage();
        image.setFileName(fileName);
        image.setOriginalName(file.getOriginalFilename());
        image.setProject(project);

        int nextOrder = getImagesByProjectId(projectId).size();
        image.setDisplayOrder(nextOrder);

        return projectImageRepository.save(image);
    }

    @Override
    public void deleteImage(Long projectId, Long imageId) {
        ProjectImage image = projectImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image introuvable avec l'id : " + imageId));

        if (!image.getProject().getId().equals(projectId)) {
            throw new ResourceNotFoundException("Cette image n'appartient pas à ce projet");
        }

        fileStorageUtils.deleteFile(image.getFileName());
        projectImageRepository.delete(image);
    }
}