package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.model.request.ChangePasswordRequest;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.presentation.screen.SettingScreen;
import com.xyz.roemahduren.util.DatabaseWorker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

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
        new DatabaseWorker<>(
                () -> {
                    ChangePasswordRequest request = new ChangePasswordRequest(
                            settingScreen.getEmailTextField().getValue(),
                            settingScreen.getPasswordField().getValue(),
                            settingScreen.getConfirmPasswordField().getValue()
                    );
                    return authService.changePassword(request);
                },
                result -> {
                    clearForm();
                    dialog.getSuccessMessageDialog("Ubah Kata Sandi baru berhasil!");
                },
                throwable -> dialog.getFailedMessageDialog(throwable.getMessage()),
                () -> {
                }
        ).execute();
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
