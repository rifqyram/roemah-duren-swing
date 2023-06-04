package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.UserCredential;
import com.xyz.roemahduren.domain.model.request.AuthRequest;
import com.xyz.roemahduren.domain.model.request.ChangePasswordRequest;
import com.xyz.roemahduren.domain.model.response.AuthResponse;
import com.xyz.roemahduren.domain.repository.UserCredentialRepository;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.infrastructure.security.PasswordEncoder;
import com.xyz.roemahduren.util.RandomGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final Connection connection;
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder, Connection connection) {
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.connection = connection;
    }

    @Override
    public AuthResponse register(AuthRequest request) {
        try {
            Optional<UserCredential> currentUser = userCredentialRepository.findByEmail(request.getEmail());

            if (currentUser.isPresent()) throw new RuntimeException("Email sudah terdaftar");

            UserCredential userCredential = new UserCredential(
                    RandomGenerator.generateUUID(),
                    request.getEmail(),
                    passwordEncoder.hashPassword(request.getPassword()), null
            );

            userCredentialRepository.save(userCredential);

            connection.commit();
            return new AuthResponse(userCredential.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        Optional<UserCredential> credentialOptional = userCredentialRepository.findByEmail(request.getEmail());

        if (!credentialOptional.isPresent()) throw new RuntimeException("Email atau Kata Sandi salah!");
        UserCredential userCredential = credentialOptional.get();

        boolean verify = passwordEncoder.verifyPassword(request.getPassword(), userCredential.getPassword());
        if (!verify) throw new RuntimeException("Email atau Kata Sandi salah!");

        return new AuthResponse(userCredential.getEmail());
    }

    @Override
    public boolean changePassword(ChangePasswordRequest request) {
        Optional<UserCredential> credentialOptional = userCredentialRepository.findByEmail(request.getEmail());

        if (!credentialOptional.isPresent()) throw new RuntimeException("Email atau Kata Sandi salah!");
        UserCredential userCredential = credentialOptional.get();

        if (!request.getPassword().equals(request.getConfirmPassword())) throw new RuntimeException("Kata Sandi dan konfirmasi tidak sesuai");
        userCredential.setPassword(passwordEncoder.hashPassword(request.getPassword()));
        userCredentialRepository.update(userCredential);

        return true;
    }
}
