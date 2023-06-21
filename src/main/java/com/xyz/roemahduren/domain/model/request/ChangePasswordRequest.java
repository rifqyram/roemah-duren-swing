package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.domain.annotation.validation.Size;

public class ChangePasswordRequest {

    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8, max = 16, message = "Kata sandi harus terdiri dari minimal 8 karakter dan maksimal 16 karakter")
    private String password;
    @NotBlank
    private String confirmPassword;

    public ChangePasswordRequest(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public ChangePasswordRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
