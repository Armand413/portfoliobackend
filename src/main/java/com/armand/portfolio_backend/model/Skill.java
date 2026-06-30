package com.armand.portfolio_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    // Catégorie de la compétence (ex: "Langage", "Framework", "Outil", "Sécurité")
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private SkillCategory category;

    // Niveau de maîtrise, de 1 à 5
    @Column(nullable = false)
    private int level;

    // Pour ordonner l'affichage des compétences dans une catégorie
    @Column(name = "display_order")
    private int displayOrder;
}