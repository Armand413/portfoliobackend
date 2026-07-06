package com.armand.portfolio_backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UpdateCredentialsRequestDTO {

    @NotBlank(message = "Le mot de passe actuel est requis")
    private String currentPassword;

    private String newUsername;
    private String newPassword;

    public String getCurrentPassword() { return currentPassword; }
    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }
    public String getNewUsername() { return newUsername; }
    public void setNewUsername(String newUsername) { this.newUsername = newUsername; }
    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}