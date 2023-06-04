package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.service.*;
import com.xyz.roemahduren.presentation.screen.*;

public class ControllerFactory {

    // Service
    private final AuthService authService;
    private final BranchService branchService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderService orderService;
    private final SupplierService supplierService;
    private final SupplierProductService supplierProductService;
    private final CustomerService customerService;

    // Screen
    private final LoginScreen loginScreen;
    private final RegisterScreen registerScreen;
    private final BranchScreen branchScreen;
    private final MainScreen mainScreen;
    private final CategoryScreen categoryScreen;
    private final ProductScreen productScreen;
    private final OrderScreen orderScreen;
    private final SupplierScreen supplierScreen;
    private final SettingScreen settingScreen;
    private final CustomerScreen customerScreen;
    private final TransactionHistoryScreen transactionHistoryScreen;

    // Utils
    private final CustomDialog customDialog;

    public ControllerFactory(AuthService authService, BranchService branchService, CategoryService categoryService, ProductService productService, OrderService orderService, SupplierService supplierService, SupplierProductService supplierProductService, CustomerService customerService, LoginScreen loginScreen, RegisterScreen registerScreen, BranchScreen branchScreen, MainScreen mainScreen, CategoryScreen categoryScreen, ProductScreen productScreen, OrderScreen orderScreen, SupplierScreen supplierScreen, SettingScreen settingScreen, CustomerScreen customerScreen, TransactionHistoryScreen transactionHistoryScreen, CustomDialog customDialog) {
        this.authService = authService;
        this.branchService = branchService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.orderService = orderService;
        this.supplierService = supplierService;
        this.supplierProductService = supplierProductService;
        this.customerService = customerService;
        this.loginScreen = loginScreen;
        this.registerScreen = registerScreen;
        this.branchScreen = branchScreen;
        this.mainScreen = mainScreen;
        this.categoryScreen = categoryScreen;
        this.productScreen = productScreen;
        this.orderScreen = orderScreen;
        this.supplierScreen = supplierScreen;
        this.settingScreen = settingScreen;
        this.customerScreen = customerScreen;
        this.transactionHistoryScreen = transactionHistoryScreen;
        this.customDialog = customDialog;
    }

    public LoginController loginController() {
        return new LoginController(loginScreen, authService, customDialog);
    }

    public RegisterController registerController() {
        return new RegisterController(registerScreen, authService, customDialog);
    }

    public BranchController branchController() {
        return new BranchController(branchService, branchScreen, customDialog);
    }

    public CategoryController categoryController() {
        return new CategoryController(categoryService, categoryScreen, customDialog);
    }

    public ProductController productController() {
        return new ProductController(productService, categoryService, branchService, productScreen, supplierProductService, customDialog);
    }

    public OrderController orderController() {
        return new OrderController(orderScreen, orderService, productService, customDialog);
    }

    public SupplierController supplierController() {
        return new SupplierController(supplierScreen, supplierService, supplierProductService, customDialog);
    }

    public SettingController settingController() {
        return new SettingController(settingScreen, authService, customDialog);
    }

    public CustomerController customerController() {
        return new CustomerController(customerScreen, customerService);
    }

    public TransactionHistoryController transactionHistoryController() {
        return new TransactionHistoryController(transactionHistoryScreen, orderService);
    }

    public MainController mainController() {
        return new MainController(
                mainScreen,
                branchController(),
                categoryController(),
                productController(),
                orderController(),
                supplierController(),
                settingController(),
                customerController(),
                transactionHistoryController(),
                customDialog);
    }

}
