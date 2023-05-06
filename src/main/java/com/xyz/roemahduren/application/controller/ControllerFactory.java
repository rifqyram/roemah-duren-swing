package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.domain.service.CategoryService;
import com.xyz.roemahduren.domain.service.ProductService;
import com.xyz.roemahduren.presentation.screen.*;

public class ControllerFactory {

    // Service
    private final AuthService authService;
    private final BranchService branchService;
    private final CategoryService categoryService;
    private final ProductService productService;

    // Screen
    private final LoginScreen loginScreen;
    private final RegisterScreen registerScreen;
    private final BranchScreen branchScreen;
    private final MainScreen mainScreen;
    private final CategoryScreen categoryScreen;
    private final ProductScreen productScreen;

    // Utils
    private final CustomDialog customDialog;

    public ControllerFactory(AuthService authService, BranchService branchService, CategoryService categoryService, ProductService productService, LoginScreen loginScreen, RegisterScreen registerScreen, BranchScreen branchScreen, MainScreen mainScreen, CategoryScreen categoryScreen, ProductScreen productScreen, CustomDialog customDialog) {
        this.authService = authService;
        this.branchService = branchService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.loginScreen = loginScreen;
        this.registerScreen = registerScreen;
        this.branchScreen = branchScreen;
        this.mainScreen = mainScreen;
        this.categoryScreen = categoryScreen;
        this.productScreen = productScreen;
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

    public CategoryController categoryController() {
        return new CategoryController(categoryService, categoryScreen, customDialog);
    }

    public ProductController productController() {
        return new ProductController(productService, categoryService, branchService, productScreen, customDialog);
    }

    public MainController mainController() {
        return new MainController(mainScreen, branchController(), categoryController(), productController());
    }

}
