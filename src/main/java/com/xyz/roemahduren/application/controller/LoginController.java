package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.model.request.AuthRequest;
import com.xyz.roemahduren.domain.model.response.AuthResponse;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.dialog.ForgotPasswordDialog;
import com.xyz.roemahduren.presentation.screen.LoginScreen;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.Utility;
import com.xyz.roemahduren.util.ValidationUtil;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.Set;

import static com.xyz.roemahduren.constant.ConstantMessage.*;

public class LoginController {

    private final LoginScreen loginScreen;
    private final AuthService authService;
    private final CustomDialog dialog;
    private final ForgotPasswordDialog forgotPasswordDialog;
    private RegisterController registerController;
    private MainController mainController;


    public LoginController(LoginScreen loginScreen, AuthService authService, CustomDialog dialog, ForgotPasswordDialog forgotPasswordDialog) {
        this.loginScreen = loginScreen;
        this.authService = authService;
        this.dialog = dialog;
        this.forgotPasswordDialog = forgotPasswordDialog;
        forgotPasswordDialog.setAuthService(authService);
        forgotPasswordDialog.setDialogMessage(dialog);
        initController();
    }

    private void initController() {
        loginScreen.getSignInBtn().addActionListener(this::signInUser);
        loginScreen.getRegisterLabelLink().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toRegisterScreen();
            }
        });
        loginScreen.getForgotPasswordLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                forgotPasswordDialog.showModal();
            }
        });
        loginScreen.getEmailTf().getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signInUser(null);
                }
            }
        });
        loginScreen.getPasswordTf().getPasswordField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signInUser(null);
                }
            }
        });
    }

    private void clearErrors() {
        loginScreen.getEmailTf().clearErrorMessage();
        loginScreen.getPasswordTf().clearErrorMessage();
    }

    private void clearForm() {
        loginScreen.getEmailTf().setValue("");
        loginScreen.getPasswordTf().setValue("");
    }

    private void signInUser(ActionEvent actionEvent) {
        clearErrors();
        LoginScreen screen = loginScreen;
        String btnText = screen.getSignInBtn().getText();

        ServiceWorker<AuthResponse> serviceWorker = new ServiceWorker<>(
                () -> {
                    SwingUtil.setLoading(loginScreen.getSignInBtn());
                    AuthRequest authRequest = new AuthRequest(screen.getEmailTf().getValue(), screen.getPasswordTf().getValue());
                    ValidationUtil.validate(authRequest);
                    return authService.login(authRequest);
                },
                authResponse -> {
                    mainController.getMainScreen().setVisible(true);
                    MainController.user = authResponse;
                    String[] email = authResponse.getEmail().split("@");
                    String username = Utility.toTitleCase(email[0]);
                    mainController.getMainScreen().getDashboardScreen().getWelcomeLabel().setText("Hi, " + username + "!");
                    loginScreen.dispose();
                    dialog.getSuccessMessageDialog(LOGIN_SUCCESS_MESSAGE);
                    clearForm();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setValidationError((ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> SwingUtil.clearPrimaryLoading(loginScreen.getSignInBtn(), btnText)
        );
        serviceWorker.execute();
    }

    private void setValidationError(ValidationException throwable) {
        LoginScreen screen = loginScreen;
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

    public LoginScreen getLoginScreen() {
        return loginScreen;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
