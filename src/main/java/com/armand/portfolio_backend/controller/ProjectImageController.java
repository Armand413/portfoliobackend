package com.armand.portfolio_backend.controller;

import com.armand.portfolio_backend.model.ProjectImage;
import com.armand.portfolio_backend.service.ProjectImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/images")
public class ProjectImageController {

    private final ProjectImageService projectImageService;

    public ProjectImageController(ProjectImageService projectImageService) {
        this.projectImageService = projectImageService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectImage>> getImages(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectImageService.getImagesByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<ProjectImage> uploadImage(@PathVariable Long projectId,
                                                    @RequestParam("file") MultipartFile file) {
        ProjectImage created = projectImageService.addImageToProject(projectId, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long projectId, @PathVariable Long imageId) {
        projectImageService.deleteImage(projectId, imageId);
        return ResponseEntity.noContent().build();
    }
}