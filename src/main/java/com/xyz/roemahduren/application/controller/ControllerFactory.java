package com.xyz.roemahduren.application.controller;

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
    private final MainScreen mainScreen;
    private final BranchScreen branchScreen;
    private final CategoryScreen categoryScreen;

    public ControllerFactory(AuthService authService, BranchService branchService, CategoryService categoryService, LoginScreen loginScreen, RegisterScreen registerScreen, MainScreen mainScreen, BranchScreen branchScreen, CategoryScreen categoryScreen) {
        this.authService = authService;
        this.branchService = branchService;
        this.categoryService = categoryService;
        this.loginScreen = loginScreen;
        this.registerScreen = registerScreen;
        this.mainScreen = mainScreen;
        this.branchScreen = branchScreen;
        this.categoryScreen = categoryScreen;
    }

    public LoginController loginController() {
        return new LoginController(loginScreen, authService);
    }

    public RegisterController registerController() {
        return new RegisterController(registerScreen, authService);
    }

    public MainController mainController() {
        return new MainController(mainScreen, branchController(), categoryController());
    }

    public BranchController branchController() {
        return new BranchController(branchScreen, branchService);
    }

    public CategoryController categoryController() {
        return new CategoryController(categoryService, categoryScreen);
    }
}
