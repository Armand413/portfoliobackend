package com.armand.portfolio_backend.util;

import com.armand.portfolio_backend.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Set;
import java.util.UUID;

@Component
public class FileStorageUtils {

    @Value("${app.upload.dir}")
    private String uploadDir;

    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png", "image/webp");
    private static final long MAX_SIZE = 5 * 1024 * 1024; // 5 Mo

    public String storeFile(MultipartFile file) {
        validateFile(file);

        String extension = getExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + extension;

        try {
            Path targetDir = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(targetDir);

            Path targetPath = targetDir.resolve(fileName).normalize();

            if (!targetPath.startsWith(targetDir)) {
                throw new ApiException("Chemin de fichier invalide");
            }

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (IOException e) {
            throw new ApiException("Erreur lors de l'enregistrement du fichier");
        }
    }

    public void deleteFile(String fileName) {
        try {
            Path targetDir = Paths.get(uploadDir).toAbsolutePath().normalize();
            Path filePath = targetDir.resolve(fileName).normalize();

            if (!filePath.startsWith(targetDir)) {
                throw new ApiException("Chemin de fichier invalide");
            }

            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new ApiException("Erreur lors de la suppression du fichier");
        }
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiException("Le fichier est vide");
        }
        if (file.getSize() > MAX_SIZE) {
            throw new ApiException("Le fichier dépasse la taille maximale autorisée (5 Mo)");
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new ApiException("Type de fichier non autorisé");
        }
    }

    private String getExtension(String originalFileName) {
        if (originalFileName == null || !originalFileName.contains(".")) {
            return "";
        }
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }
}