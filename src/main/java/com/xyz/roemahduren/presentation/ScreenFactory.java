package com.xyz.roemahduren.presentation;

import com.xyz.roemahduren.presentation.screen.LoginScreen;
import com.xyz.roemahduren.presentation.screen.RegisterScreen;

public class ScreenFactory {
    public LoginScreen loginScreen() {
        return new LoginScreen();
    }

    public RegisterScreen registerScreen() {
        return new RegisterScreen();
    }
}
