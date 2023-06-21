package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.model.request.AuthRequest;
import com.xyz.roemahduren.domain.model.request.ChangePasswordRequest;
import com.xyz.roemahduren.domain.model.response.AuthResponse;

public interface AuthService {

    AuthResponse register(AuthRequest request);

    AuthResponse login(AuthRequest request);

    AuthResponse findByEmail(String email);

    boolean changePassword(ChangePasswordRequest request);

}
