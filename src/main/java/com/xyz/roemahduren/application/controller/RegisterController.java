package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.domain.model.request.AuthRequest;
import com.xyz.roemahduren.domain.model.response.AuthResponse;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.screen.RegisterScreen;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

public class RegisterController {

    private final RegisterScreen registerScreen;
    private final AuthService authService;
    private LoginController loginController;

    public RegisterController(RegisterScreen registerScreen, AuthService authService) {
        this.registerScreen = registerScreen;
        this.authService = authService;

        initController();
    }


    public void initController() {
        registerScreen.getLoginLabelLink().addMouseListener(mouseListener());
        registerScreen.getRegisterBtn().addActionListener(this::registerUser);
    }

    private void clearErrors() {
        registerScreen.getErrorEmailLabel().setText("");
        registerScreen.getErrorPasswordLabel().setText("");
    }

    private void registerUser(ActionEvent actionEvent) {
        clearErrors();
        RegisterScreen screen = registerScreen;
        String btnText = screen.getRegisterBtn().getText();

        DatabaseWorker<AuthResponse> databaseWorker = new DatabaseWorker<>(
                () -> {
                    setLoading();
                    AuthRequest authRequest = new AuthRequest(screen.getTfEmail().getText(), screen.getTfPassword().getStringPassword());
                    ValidationUtil.validate(authRequest);
                    return authService.register(authRequest);
                },
                authResponse -> JOptionPane.showMessageDialog(null, "Register Successfully!"),
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

    private static void setValidationError(RegisterScreen screen, ValidationException throwable) {
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
        RegisterScreen screen = registerScreen;
        screen.getRegisterBtn().setText(btnText);
        screen.getRegisterBtn().setEnabled(true);
        screen.getRegisterBtn().setBackground(new Color(0xF6921E));
        screen.getRegisterBtn().setBorderColor(new Color(0xF6921E));
    }

    private void setLoading() {
        RegisterScreen screen = registerScreen;
        screen.getRegisterBtn().setText("Loading...");
        screen.getRegisterBtn().setBackground(new Color(0xF4F4F4));
        screen.getRegisterBtn().setBorderColor(new Color(0xF4F4F4));
        screen.getRegisterBtn().setEnabled(false);
    }

    private void toLoginScreen() {
        registerScreen.setVisible(false);
        loginController.setVisible(true);
        registerScreen.dispose();
    }

    public void setVisible(boolean isVisible) {
        registerScreen.setVisible(isVisible);
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                toLoginScreen();
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
