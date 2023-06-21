/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.xyz.roemahduren.presentation.component.dialog;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.model.request.ChangePasswordRequest;
import com.xyz.roemahduren.domain.model.response.AuthResponse;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.component.input.RoundedPasswordFieldPanel;
import com.xyz.roemahduren.presentation.component.input.RoundedTextFieldPanel;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.swing.*;

/**
 * @author user
 */
public class ForgotPasswordDialog extends JDialog {

    /**
     * Creates new form CustomConfirmDialog
     */

    private AuthService authService;
    private CustomDialog dialog;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public void setDialogMessage(CustomDialog dialogMessage) {
        this.dialog = dialogMessage;
    }

    public ForgotPasswordDialog() {
        initComponents();
        getContentPane().setBackground(new Color(0xFFFFFE));
        SwingUtil.centerWindow(this);
        setResizable(false);
        passwordField.setVisible(false);
        confirmPasswordField.setVisible(false);
        changePasswordBtn.setVisible(false);
        initEvents();
    }

    public void showModal() {
        clearForm();
        setVisible(true);
        setModal(true);
    }

    private void initEvents() {
        checkEmailBtn.addActionListener(this::checkEmailExist);
        changePasswordBtn.addActionListener(this::changePassword);
    }

    private void changePassword(ActionEvent actionEvent) {
        clearErrorMessages();
        new ServiceWorker<>(
                () -> {
                    SwingUtil.setLoading(changePasswordBtn);
                    if (!passwordField.getValue().equals(confirmPasswordField.getValue())) {
                        throw new ValidationException(new HashSet<>(Collections.singletonList(new ErrorValidationModel("confirmPassword", new HashSet<>(Collections.singletonList("Password tidak match"))))));
                    }
                    ChangePasswordRequest request = new ChangePasswordRequest(
                            emailTf.getValue(),
                            passwordField.getValue(),
                            confirmPasswordField.getValue()
                    );
                    ValidationUtil.validate(request);
                    return authService.changePassword(request);
                },
                result -> {
                    dialog.getSuccessMessageDialog("Ubah Kata Sandi baru berhasil!");
                    setVisible(false);
                    setModal(false);
                    clearForm();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessages((ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> SwingUtil.clearPrimaryLoading(changePasswordBtn, "Ganti Password")
        ).execute();
    }

    private void setErrorMessages(ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            String message = ValidationUtil.getMessage(validationModel.getMessages());

            if (validationModel.getPath().equals("email")) {
                emailTf.setErrorMessage(message);
            }
            if (validationModel.getPath().equals("password")) {
                passwordField.setErrorMessage(message);
            }
            if (validationModel.getPath().equals("confirmPassword")) {
                confirmPasswordField.setErrorMessage(message);
            }
        }
        return;
    }

    private void clearForm() {
        emailTf.setValue("");
        passwordField.setValue("");
        confirmPasswordField.setValue("");
    }

    private void checkEmailExist(ActionEvent actionEvent) {
        clearErrorMessages();
        new ServiceWorker<>(
                () -> {
                    SwingUtil.setLoading(checkEmailBtn);
                    AuthResponse user = authService.findByEmail(emailTf.getValue());

                    if (Objects.isNull(user)) {
                        emailTf.setErrorMessage("Email tidak ditemukan");
                    }

                    return user;
                },
                authResponse -> {
                    passwordField.setVisible(true);
                    confirmPasswordField.setVisible(true);
                    changePasswordBtn.setVisible(true);
                },
                throwable -> dialog.getFailedMessageDialog(throwable.getMessage()),
                () -> SwingUtil.clearSecondaryLoading(checkEmailBtn, "Cek Email")
        ).execute();
    }

    private void clearErrorMessages() {
        emailTf.clearErrorMessage();
        passwordField.clearErrorMessage();
        confirmPasswordField.clearErrorMessage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changePasswordBtn = new RoundedButton();
        emailTf = new RoundedTextFieldPanel();
        checkEmailBtn = new RoundedButton();
        passwordField = new RoundedPasswordFieldPanel();
        confirmPasswordField = new RoundedPasswordFieldPanel();
        title = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new Color(255, 255, 254));
        setMaximumSize(new Dimension(400, 400));
        setMinimumSize(new Dimension(400, 200));

        changePasswordBtn.setText("Ubah Password");
        changePasswordBtn.setBorderColor(new Color(187, 33, 36));
        changePasswordBtn.setColor(new Color(187, 33, 36));
        changePasswordBtn.setColorClick(new Color(141, 25, 27));
        changePasswordBtn.setColorOver(new Color(166, 29, 31));
        changePasswordBtn.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        emailTf.setBackground(new Color(255, 255, 254));
        emailTf.setLabelErrorText("");
        emailTf.setLabelText("Email");

        checkEmailBtn.setText("Cek Email");
        checkEmailBtn.setBorderPainted(false);
        checkEmailBtn.setColorClick(new Color(130, 148, 96));
        checkEmailBtn.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        passwordField.setBackground(new Color(255, 255, 254));
        passwordField.setLabelErrorText("");
        passwordField.setLabelText("Kata Sandi Baru");
        passwordField.setValue("");

        confirmPasswordField.setBackground(new Color(255, 255, 254));
        confirmPasswordField.setLabelErrorText("");
        confirmPasswordField.setLabelText("Konfirmasi Kata Sandi Baru");
        confirmPasswordField.setValue("");

        title.setFont(new Font("Helvetica Neue", 1, 18)); // NOI18N
        title.setForeground(new Color(2, 8, 38));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("Lupa Password");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(title, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(emailTf, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(changePasswordBtn, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(checkEmailBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(confirmPasswordField, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(title)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(emailTf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checkEmailBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(changePasswordBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoundedButton changePasswordBtn;
    private RoundedButton checkEmailBtn;
    private RoundedPasswordFieldPanel confirmPasswordField;
    private RoundedTextFieldPanel emailTf;
    private RoundedPasswordFieldPanel passwordField;
    private JLabel title;
    // End of variables declaration//GEN-END:variables
}
