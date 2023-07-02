package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.model.request.AuthRequest;
import com.xyz.roemahduren.domain.model.response.AuthResponse;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.screen.RegisterScreen;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import java.awt.event.*;
import java.util.Set;

import static com.xyz.roemahduren.constant.ConstantMessage.REGISTER;

public class RegisterController {

    private final RegisterScreen registerScreen;
    private final AuthService authService;
    private final CustomDialog dialog;
    private LoginController loginController;

    public RegisterController(RegisterScreen registerScreen, AuthService authService, CustomDialog dialog) {
        this.registerScreen = registerScreen;
        this.authService = authService;
        this.dialog = dialog;

        initController();
    }


    public void initController() {
        registerScreen.getLoginLabelLink().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toLoginScreen();
            }
        });
        registerScreen.getRegisterBtn().addActionListener(this::registerUser);
        registerScreen.getEmailTf().getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    registerUser(null);
                }
            }
        });
        registerScreen.getPasswordTf().getPasswordField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    registerUser(null);
                }
            }
        });
    }

    private void clearErrors() {
        registerScreen.getEmailTf().clearErrorMessage();
        registerScreen.getPasswordTf().clearErrorMessage();
    }

    private void clearForm() {
        registerScreen.getEmailTf().setValue("");
        registerScreen.getPasswordTf().setValue("");
    }


    private void registerUser(ActionEvent actionEvent) {
        clearErrors();
        RegisterScreen screen = registerScreen;
        String btnText = screen.getRegisterBtn().getText();

        ServiceWorker<AuthResponse> serviceWorker = new ServiceWorker<>(
                () -> {
                    SwingUtil.setLoading(registerScreen.getRegisterBtn());
                    AuthRequest authRequest = new AuthRequest(screen.getEmailTf().getValue(), screen.getPasswordTf().getValue());
                    ValidationUtil.validate(authRequest);
                    return authService.register(authRequest);
                },
                authResponse -> {
                    toLoginScreen();
                    dialog.getSuccessMessageDialog(REGISTER);
                    clearForm();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setValidationError(screen, (ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> SwingUtil.clearPrimaryLoading(registerScreen.getRegisterBtn(), btnText)
        );
        serviceWorker.execute();
    }

    private static void setValidationError(RegisterScreen screen, ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            Set<String> messages = validationModel.getMessages();
            String message = ValidationUtil.getHtmlMessage(messages, 199);

            if (validationModel.getPath().equals("email")) {
                screen.getEmailTf().setErrorMessage(message);
            } else {
                screen.getPasswordTf().setErrorMessage(message);
            }
        }
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

}
