package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.presentation.screen.LoginScreen;
import com.xyz.roemahduren.presentation.screen.RegisterScreen;

public class ControllerFactory {

    private final AuthService authService;
    private final LoginScreen loginScreen;
    private final RegisterScreen registerScreen;

    public ControllerFactory(AuthService authService, LoginScreen loginScreen, RegisterScreen registerScreen) {
        this.authService = authService;
        this.loginScreen = loginScreen;
        this.registerScreen = registerScreen;
    }

    public LoginController loginController() {
        return new LoginController(loginScreen, authService);
    }

    public RegisterController registerController() {
        return new RegisterController(registerScreen, authService);
    }
}
