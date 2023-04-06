package com.xyz.roemahduren.infrastructure.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncoder {

    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 11;

    public String hashPassword(String password) {
        byte[] salt = generateSalt();
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = hashWithSalt(md, password.getBytes(StandardCharsets.UTF_8), salt, ITERATIONS);
            String saltString = Base64.getEncoder().encodeToString(salt);
            String hashedString = Base64.getEncoder().encodeToString(hashedBytes);
            hashedPassword = String.format("$2a$%d$%s$%s", ITERATIONS, saltString, hashedString);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    public boolean verifyPassword(String password, String hashedPassword) {
        String[] parts = hashedPassword.split("\\$");
        int iterations = Integer.parseInt(parts[2]);
        byte[] salt = Base64.getDecoder().decode(parts[3]);
        byte[] hashedBytes = Base64.getDecoder().decode(parts[4]);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] calculatedHash = hashWithSalt(md, password.getBytes(StandardCharsets.UTF_8), salt, iterations);
            return MessageDigest.isEqual(hashedBytes, calculatedHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] hashWithSalt(MessageDigest md, byte[] input, byte[] salt, int iterations) {
        md.update(salt);
        byte[] hash = input;
        for (int i = 0; i < iterations; i++) {
            hash = md.digest(hash);
            md.reset();
            md.update(salt);
        }
        return hash;
    }

}
