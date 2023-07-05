package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.model.request.ChangePasswordRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.screen.SettingScreen;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import java.awt.event.ActionEvent;
import java.util.*;

public class SettingController {

    private final SettingScreen settingScreen;
    private final AuthService authService;
    private final CustomDialog dialog;


    public SettingController(SettingScreen settingScreen, AuthService authService, CustomDialog dialog) {
        this.settingScreen = settingScreen;
        this.authService = authService;
        this.dialog = dialog;
        initController();
        initForm();
    }

    private void initController() {
        settingScreen.getSaveBtn().addActionListener(this::saveAuthUser);
    }

    private void initForm() {
        if (!Objects.isNull(MainController.user))
            settingScreen.getEmailTextField().setValue(MainController.user.getEmail());
    }

    private void saveAuthUser(ActionEvent actionEvent) {
        new ServiceWorker<>(
                () -> {
                    SwingUtil.setLoading(settingScreen.getSaveBtn());
                    if (!settingScreen.getPasswordField().getValue().equals(settingScreen.getConfirmPasswordField().getValue())) {
                        throw new ValidationException(new HashSet<>(Collections.singletonList(new ErrorValidationModel("confirmPassword", new HashSet<>(Collections.singletonList("Password tidak match"))))));
                    }

                    ChangePasswordRequest request = new ChangePasswordRequest(
                            settingScreen.getEmailTextField().getValue(),
                            settingScreen.getPasswordField().getValue(),
                            settingScreen.getConfirmPasswordField().getValue()
                    );
                    ValidationUtil.validate(request);
                    return authService.changePassword(request);
                },
                result -> {
                    clearForm();
                    dialog.getSuccessMessageDialog("Ubah Kata Sandi baru berhasil!");
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessages((ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> SwingUtil.clearSecondaryLoading(settingScreen.getSaveBtn(), "Simpan Password")
        ).execute();
    }

    private void setErrorMessages(ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            String message = ValidationUtil.getMessage(validationModel.getMessages());

            if (validationModel.getPath().equals("email")) {
                settingScreen.getEmailTextField().setErrorMessage(message);
            }
            if (validationModel.getPath().equals("password")) {
                settingScreen.getPasswordField().setErrorMessage(message);
            }
            if (validationModel.getPath().equals("confirmPassword")) {
                settingScreen.getConfirmPasswordField().setErrorMessage(message);
            }
        }
        return;
    }

    private void clearForm() {
        settingScreen.getPasswordField().setValue("");
        settingScreen.getConfirmPasswordField().setValue("");
    }


    public SettingScreen getSettingScreen() {
        initForm();
        return settingScreen;
    }
}
