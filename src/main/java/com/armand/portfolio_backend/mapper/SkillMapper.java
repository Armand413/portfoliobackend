package com.armand.portfolio_backend.mapper;

import com.armand.portfolio_backend.dto.request.SkillRequestDTO;
import com.armand.portfolio_backend.dto.response.SkillResponseDTO;
import com.armand.portfolio_backend.model.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

    public SkillResponseDTO toDTO(Skill skill) {
        SkillResponseDTO dto = new SkillResponseDTO();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        dto.setCategory(skill.getCategory());
        dto.setLevel(skill.getLevel());
        dto.setDisplayOrder(skill.getDisplayOrder());
        return dto;
    }

    public Skill toEntity(SkillRequestDTO dto) {
        Skill skill = new Skill();
        skill.setName(dto.getName());
        skill.setCategory(dto.getCategory());
        skill.setLevel(dto.getLevel());
        skill.setDisplayOrder(dto.getDisplayOrder());
        return skill;
    }
}