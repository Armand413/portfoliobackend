package com.armand.portfolio_backend.service.impl;

import com.armand.portfolio_backend.exception.ResourceNotFoundException;
import com.armand.portfolio_backend.model.Skill;
import com.armand.portfolio_backend.repository.SkillRepository;
import com.armand.portfolio_backend.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAllByOrderByDisplayOrderAsc();
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compétence introuvable avec l'id : " + id));
    }

    @Override
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill updatedSkill) {
        Skill existing = getSkillById(id);

        existing.setName(updatedSkill.getName());
        existing.setCategory(updatedSkill.getCategory());
        existing.setLevel(updatedSkill.getLevel());
        existing.setDisplayOrder(updatedSkill.getDisplayOrder());

        return skillRepository.save(existing);
    }

    @Override
    public void deleteSkill(Long id) {
        Skill existing = getSkillById(id);
        skillRepository.delete(existing);
    }
}