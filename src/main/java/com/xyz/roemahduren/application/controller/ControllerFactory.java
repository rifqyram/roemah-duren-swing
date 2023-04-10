package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.application.controller.branch.BranchController;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.presentation.screen.BranchScreen;
import com.xyz.roemahduren.presentation.screen.LoginScreen;
import com.xyz.roemahduren.presentation.screen.MainScreen;
import com.xyz.roemahduren.presentation.screen.RegisterScreen;

public class ControllerFactory {

    // Service
    private final AuthService authService;
    private final BranchService branchService;

    // Screen
    private final LoginScreen loginScreen;
    private final RegisterScreen registerScreen;
    private final MainScreen mainScreen;
    private final BranchScreen branchScreen;

    public ControllerFactory(AuthService authService, BranchService branchService, LoginScreen loginScreen, RegisterScreen registerScreen, MainScreen mainScreen, BranchScreen branchScreen) {
        this.authService = authService;
        this.branchService = branchService;
        this.loginScreen = loginScreen;
        this.registerScreen = registerScreen;
        this.mainScreen = mainScreen;
        this.branchScreen = branchScreen;
    }

    public LoginController loginController() {
        return new LoginController(loginScreen, authService);
    }

    public RegisterController registerController() {
        return new RegisterController(registerScreen, authService);
    }

    public MainController mainController() {
        return new MainController(mainScreen, branchController());
    }

    public BranchController branchController() {
        return new BranchController(branchScreen, branchService);
    }
}
