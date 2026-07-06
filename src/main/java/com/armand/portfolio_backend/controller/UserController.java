package com.armand.portfolio_backend.controller;

import com.armand.portfolio_backend.dto.request.UpdateCredentialsRequestDTO;
import com.armand.portfolio_backend.dto.response.LoginResponseDTO;
import com.armand.portfolio_backend.exception.ApiException;
import com.armand.portfolio_backend.exception.ResourceNotFoundException;
import com.armand.portfolio_backend.model.User;
import com.armand.portfolio_backend.repository.UserRepository;
import com.armand.portfolio_backend.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PutMapping("/me")
    public ResponseEntity<LoginResponseDTO> updateCredentials(
            @Valid @RequestBody UpdateCredentialsRequestDTO request,
            Authentication authentication) {

        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        // Vérification obligatoire du mot de passe actuel avant toute modification
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new ApiException("Mot de passe actuel incorrect");
        }

        if (request.getNewUsername() != null && !request.getNewUsername().isBlank()) {
            user.setUsername(request.getNewUsername());
        }
        if (request.getNewPassword() != null && !request.getNewPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        userRepository.save(user);

        // Nouveau token car le username (subject du JWT) a pu changer
        String newToken = jwtUtils.generateToken(user.getUsername());
        return ResponseEntity.ok(new LoginResponseDTO(newToken, user.getUsername()));
    }
}