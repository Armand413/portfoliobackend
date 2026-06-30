package com.armand.portfolio_backend.service;

import com.armand.portfolio_backend.model.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> getAllSkills();

    Skill getSkillById(Long id);

    Skill createSkill(Skill skill);

    Skill updateSkill(Long id, Skill updatedSkill);

    void deleteSkill(Long id);
}