package com.armand.portfolio_backend.service;

import com.armand.portfolio_backend.model.ProjectImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectImageService {

    List<ProjectImage> getImagesByProjectId(Long projectId);

    ProjectImage addImageToProject(Long projectId, MultipartFile file);

    void deleteImage(Long projectId, Long imageId);
}