package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.Email;
import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.domain.annotation.validation.Size;

public class AuthRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 16, message = "Kata sandi harus terdiri dari minimal 8 karakter dan maksimal 16 karakter")
    private String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthRequest() {
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
}
