package com.armand.portfolio_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est requis")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "L'email est requis")
    @Email(message = "L'email doit être valide")
    @Column(nullable = false, length = 150)
    private String email;

    @NotBlank(message = "Le message est requis")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Pour savoir si tu as déjà traité/lu ce message
    @Column(nullable = false)
    private boolean read = false;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}