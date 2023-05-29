package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.model.request.AuthRequest;
import com.xyz.roemahduren.domain.model.response.AuthResponse;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.screen.LoginScreen;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import static com.xyz.roemahduren.constant.ConstantMessage.*;

public class LoginController {

    private final LoginScreen loginScreen;
    private final AuthService authService;
    private final CustomDialog dialog;
    private RegisterController registerController;
    private MainController mainController;

    public LoginController(LoginScreen loginScreen, AuthService authService, CustomDialog dialog) {
        this.loginScreen = loginScreen;
        this.authService = authService;
        this.dialog = dialog;
        initController();
        loginScreen.setVisible(true);
    }

    private void initController() {
        loginScreen.getSignInBtn().addActionListener(this::signInUser);
        loginScreen.getRegisterLabelLink().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toRegisterScreen();
            }
        });
    }

    private void clearErrors() {
        loginScreen.getEmailTf().clearErrorMessage();
        loginScreen.getPasswordTf().clearErrorMessage();
    }

    private void signInUser(ActionEvent actionEvent) {
        clearErrors();
        LoginScreen screen = loginScreen;
        String btnText = screen.getSignInBtn().getText();

        DatabaseWorker<AuthResponse> databaseWorker = new DatabaseWorker<>(
                () -> {
                    SwingUtil.setLoading(loginScreen.getSignInBtn());
                    AuthRequest authRequest = new AuthRequest(screen.getEmailTf().getValue(), screen.getPasswordTf().getValue());
                    ValidationUtil.validate(authRequest);
                    return authService.login(authRequest);
                },
                authResponse -> {
                    mainController.getMainScreen().setVisible(true);
                    loginScreen.dispose();
                    dialog.getSuccessMessageDialog(LOGIN_SUCCESS_MESSAGE);
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setValidationError(screen, (ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> SwingUtil.clearPrimaryLoading(loginScreen.getSignInBtn(), btnText)
        );
        databaseWorker.execute();
    }

    private static void setValidationError(LoginScreen screen, ValidationException throwable) {
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

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
