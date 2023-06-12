package com.xyz.roemahduren.presentation;

import com.xyz.roemahduren.presentation.component.dialog.CustomConfirmDialog;
import com.xyz.roemahduren.presentation.component.dialog.CustomDialogMessage;
import com.xyz.roemahduren.presentation.screen.*;

public class ScreenFactory {
    public LoginScreen loginScreen() {
        return new LoginScreen();
    }

    public RegisterScreen registerScreen() {
        return new RegisterScreen();
    }

    public BranchScreen branchScreen() {
        return new BranchScreen();
    }

    public CustomConfirmDialog customConfirmDialog() {
        return new CustomConfirmDialog();
    }

    public CustomDialogMessage customDialogMessage() {
        return new CustomDialogMessage();
    }

    public CategoryScreen categoryScreen() {
        return new CategoryScreen();
    }

    public ProductScreen productScreen() {
        return new ProductScreen();
    }

    public OrderScreen orderScreen() {
        return new OrderScreen();
    }

    public SupplierScreen supplierScreen() {
        return new SupplierScreen();
    }

    public SettingScreen settingScreen() {
        return new SettingScreen();
    }

    public CustomerScreen customerScreen() {
        return new CustomerScreen();
    }

    public TransactionHistoryScreen transactionHistoryScreen() {
        return new TransactionHistoryScreen();
    }

    public MainScreen mainScreen() {
        return new MainScreen();
    }
}
