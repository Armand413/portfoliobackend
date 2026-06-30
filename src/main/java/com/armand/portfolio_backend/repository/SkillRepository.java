package com.armand.portfolio_backend.repository;

import com.armand.portfolio_backend.model.Skill;
import com.armand.portfolio_backend.model.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByCategory(SkillCategory category);

    List<Skill> findAllByOrderByDisplayOrderAsc();
}