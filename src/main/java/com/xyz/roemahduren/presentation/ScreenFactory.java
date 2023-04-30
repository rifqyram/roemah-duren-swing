package com.xyz.roemahduren.presentation;

import com.xyz.roemahduren.presentation.screen.*;

public class ScreenFactory {
    public LoginScreen loginScreen() {
        return new LoginScreen();
    }

    public RegisterScreen registerScreen() {
        return new RegisterScreen();
    }

    public MainScreen mainScreen() {
        return new MainScreen();
    }

}
