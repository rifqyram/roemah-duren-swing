package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.domain.service.CategoryService;
import com.xyz.roemahduren.presentation.screen.*;

public class ControllerFactory {

    // Service
    private final AuthService authService;
    private final BranchService branchService;
    private final CategoryService categoryService;

    // Screen
    private final LoginScreen loginScreen;
    private final RegisterScreen registerScreen;
    private final BranchScreen branchScreen;
    private final MainScreen mainScreen;

    // Utils
    private final CustomDialog customDialog;

    public ControllerFactory(AuthService authService, BranchService branchService, CategoryService categoryService, LoginScreen loginScreen, RegisterScreen registerScreen, BranchScreen branchScreen, MainScreen mainScreen, CustomDialog customDialog) {
        this.authService = authService;
        this.branchService = branchService;
        this.categoryService = categoryService;
        this.loginScreen = loginScreen;
        this.registerScreen = registerScreen;
        this.branchScreen = branchScreen;
        this.mainScreen = mainScreen;
        this.customDialog = customDialog;
    }

    public LoginController loginController() {
        return new LoginController(loginScreen, authService);
    }

    public RegisterController registerController() {
        return new RegisterController(registerScreen, authService);
    }

    public BranchController branchController() {
        return new BranchController(branchService, branchScreen, customDialog);
    }

    public MainController mainController() {
        return new MainController(mainScreen, branchController());
    }

}
