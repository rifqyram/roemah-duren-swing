package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.model.request.ChangePasswordRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.screen.SettingScreen;
import com.xyz.roemahduren.presentation.screen.SettingScreen2;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import java.awt.event.ActionEvent;
import java.util.*;

public class SettingController {

    private final SettingScreen2 settingScreen2;
    private final AuthService authService;
    private final CustomDialog dialog;

    public SettingController(SettingScreen2 settingScreen22, AuthService authService, CustomDialog dialog) {
        this.settingScreen2 = settingScreen22;
        this.authService = authService;
        this.dialog = dialog;
        settingScreen2.getEmailTextField().getTextField().setEnabled(false);
        initController();
        initForm();
    }

    private void initController() {
        settingScreen2.getSaveBtn().addActionListener(this::saveAuthUser);
    }

    private void initForm() {
        if (!Objects.isNull(MainController.user))
            settingScreen2.getEmailTextField().setValue(MainController.user.getEmail());
    }

    private void saveAuthUser(ActionEvent actionEvent) {
        new ServiceWorker<>(
                () -> {
                    SwingUtil.setLoading(settingScreen2.getSaveBtn());
                    if (!settingScreen2.getPasswordField().getValue().equals(settingScreen2.getConfirmPasswordField().getValue())) {
                        throw new ValidationException(new HashSet<>(Collections.singletonList(new ErrorValidationModel("confirmPassword", new HashSet<>(Collections.singletonList("Password tidak match"))))));
                    }

                    ChangePasswordRequest request = new ChangePasswordRequest(
                            settingScreen2.getEmailTextField().getValue(),
                            settingScreen2.getPasswordField().getValue(),
                            settingScreen2.getConfirmPasswordField().getValue()
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
                () -> SwingUtil.clearSecondaryLoading(settingScreen2.getSaveBtn(), "Simpan Password")
        ).execute();
    }

    private void setErrorMessages(ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            String message = ValidationUtil.getMessage(validationModel.getMessages());

            if (validationModel.getPath().equals("email")) {
                settingScreen2.getEmailTextField().setErrorMessage(message);
            }
            if (validationModel.getPath().equals("password")) {
                settingScreen2.getPasswordField().setErrorMessage(message);
            }
            if (validationModel.getPath().equals("confirmPassword")) {
                settingScreen2.getConfirmPasswordField().setErrorMessage(message);
            }
        }
        return;
    }

    private void clearForm() {
        settingScreen2.getPasswordField().setValue("");
        settingScreen2.getConfirmPasswordField().setValue("");
        settingScreen2.getPasswordField().clearErrorMessage();
        settingScreen2.getConfirmPasswordField().clearErrorMessage();
    }


    public SettingScreen2 getSettingScreen() {
        initForm();
        clearForm();
        return settingScreen2;
    }
}
