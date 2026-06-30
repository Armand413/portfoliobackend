package com.armand.portfolio_backend.controller;

import com.armand.portfolio_backend.dto.request.SkillRequestDTO;
import com.armand.portfolio_backend.dto.response.SkillResponseDTO;
import com.armand.portfolio_backend.mapper.SkillMapper;
import com.armand.portfolio_backend.model.Skill;
import com.armand.portfolio_backend.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;
    private final SkillMapper skillMapper;

    public SkillController(SkillService skillService, SkillMapper skillMapper) {
        this.skillService = skillService;
        this.skillMapper = skillMapper;
    }

    @GetMapping
    public ResponseEntity<List<SkillResponseDTO>> getAllSkills() {
        List<SkillResponseDTO> result = skillService.getAllSkills().stream()
                .map(skillMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponseDTO> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(skillMapper.toDTO(skillService.getSkillById(id)));
    }

    @PostMapping
    public ResponseEntity<SkillResponseDTO> createSkill(@Valid @RequestBody SkillRequestDTO requestDTO) {
        Skill skill = skillMapper.toEntity(requestDTO);
        Skill created = skillService.createSkill(skill);
        return ResponseEntity.status(HttpStatus.CREATED).body(skillMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillResponseDTO> updateSkill(@PathVariable Long id, @Valid @RequestBody SkillRequestDTO requestDTO) {
        Skill skill = skillMapper.toEntity(requestDTO);
        Skill updated = skillService.updateSkill(id, skill);
        return ResponseEntity.ok(skillMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}