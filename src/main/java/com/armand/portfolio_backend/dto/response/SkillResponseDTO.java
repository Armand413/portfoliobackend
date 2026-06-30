package com.armand.portfolio_backend.dto.response;

import com.armand.portfolio_backend.model.SkillCategory;

public class SkillResponseDTO {

    private Long id;
    private String name;
    private SkillCategory category;
    private int level;
    private int displayOrder;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public SkillCategory getCategory() { return category; }
    public void setCategory(SkillCategory category) { this.category = category; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
}