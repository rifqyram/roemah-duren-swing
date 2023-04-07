package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.domain.model.request.AuthRequest;
import com.xyz.roemahduren.domain.model.response.AuthResponse;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.screen.LoginScreen;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

public class LoginController {

    private final LoginScreen loginScreen;
    private final AuthService authService;
    private RegisterController registerController;

    public LoginController(LoginScreen loginScreen,  AuthService authService) {
        this.loginScreen = loginScreen;
        this.authService = authService;

        initController();
    }

    private void initController() {
        loginScreen.getSignInBtn().addActionListener(this::signInUser);
        loginScreen.getRegisterLabelLink().addMouseListener(mouseListener());
    }

    private void clearErrors() {
        loginScreen.getErrorEmailLabel().setText("");
        loginScreen.getErrorPasswordLabel().setText("");
    }

    private void signInUser(ActionEvent actionEvent) {
        clearErrors();
        LoginScreen screen = loginScreen;
        String btnText = screen.getSignInBtn().getText();

        DatabaseWorker<AuthResponse> databaseWorker = new DatabaseWorker<>(
                () -> {
                    setLoading();
                    AuthRequest authRequest = new AuthRequest(screen.getTfEmail().getText(), screen.getTfPassword().getStringPassword());
                    ValidationUtil.validate(authRequest);
                    return authService.login(authRequest);
                },
                authResponse -> JOptionPane.showMessageDialog(null, "Login Successfully!"),
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setValidationError(screen, (ValidationException) throwable);
                    } else {
                        JOptionPane.showMessageDialog(null, throwable.getMessage());
                    }
                },
                () -> clearLoading(btnText)
        );
        databaseWorker.execute();
    }

    private static void setValidationError(LoginScreen screen, ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            Set<String> messages = validationModel.getMessages();
            String message = ValidationUtil.getHtmlMessage(messages, 199);

            if (validationModel.getPath().equals("email")) {
                screen.getErrorEmailLabel().setText(message);
            } else {
                screen.getErrorPasswordLabel().setText(message);
            }
        }
    }

    private void clearLoading(String btnText) {
        LoginScreen screen = loginScreen;
        screen.getSignInBtn().setText(btnText);
        screen.getSignInBtn().setEnabled(true);
        screen.getSignInBtn().setBackground(new Color(0xF6921E));
        screen.getSignInBtn().setBorderColor(new Color(0xF6921E));
    }

    private void setLoading() {
        LoginScreen screen = loginScreen;
        screen.getSignInBtn().setText("Loading...");
        screen.getSignInBtn().setBackground(new Color(0xF4F4F4));
        screen.getSignInBtn().setBorderColor(new Color(0xF4F4F4));
        screen.getSignInBtn().setEnabled(false);
    }

    private void toRegisterScreen() {
        loginScreen.setVisible(false);
        registerController.setVisible(true);
        loginScreen.dispose();
    }

    public void setVisible(boolean isVisible) {
        loginScreen.setVisible(isVisible);
    }

    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                toRegisterScreen();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
    }
}
