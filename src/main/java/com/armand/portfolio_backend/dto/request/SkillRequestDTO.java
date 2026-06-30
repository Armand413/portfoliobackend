package com.armand.portfolio_backend.dto.request;

import com.armand.portfolio_backend.model.SkillCategory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class SkillRequestDTO {

    @NotBlank(message = "Le nom est requis")
    private String name;

    private SkillCategory category;

    @Min(value = 1, message = "Le niveau doit être entre 1 et 5")
    @Max(value = 5, message = "Le niveau doit être entre 1 et 5")
    private int level;

    private int displayOrder;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public SkillCategory getCategory() { return category; }
    public void setCategory(SkillCategory category) { this.category = category; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
}