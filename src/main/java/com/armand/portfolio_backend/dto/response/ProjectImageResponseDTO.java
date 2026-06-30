package com.armand.portfolio_backend.dto.response;

public class ProjectImageResponseDTO {

    private Long id;
    private String fileName;
    private int displayOrder;

    public ProjectImageResponseDTO(Long id, String fileName, int displayOrder) {
        this.id = id;
        this.fileName = fileName;
        this.displayOrder = displayOrder;
    }

    public Long getId() { return id; }
    public String getFileName() { return fileName; }
    public int getDisplayOrder() { return displayOrder; }
}