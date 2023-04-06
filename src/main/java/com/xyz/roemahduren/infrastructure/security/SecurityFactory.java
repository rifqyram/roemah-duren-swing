package com.xyz.roemahduren.infrastructure.security;

public class SecurityFactory {

    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder();
    }

}
