package com.xyz.roemahduren.presentation;

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

    public MainScreen mainScreen() {
        return new MainScreen();
    }

    public CategoryScreen categoryScreen() {
        return new CategoryScreen();
    }
}
