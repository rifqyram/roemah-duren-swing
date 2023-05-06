package com.xyz.roemahduren.constant;

import com.xyz.roemahduren.presentation.component.CustomConfirmDialog;
import com.xyz.roemahduren.presentation.component.CustomDialogMessage;

import java.net.URL;

public class CustomDialog {
    private final CustomDialogMessage dialogMessage;
    private final CustomConfirmDialog confirmDialog;

    public static final String SUCCESS = "Success";
    public static final String FAIL = "Error";
    public static final String WARNING = "Warning";
    public static final String INFO = "Info";

    public static final URL SUCCESS_IMAGE = CustomDialog.class.getResource("/images/Success.png");
    public static final URL FAIL_IMAGE = CustomDialog.class.getResource("/images/Danger.png");
    public static final URL WARNING_IMAGE = CustomDialog.class.getResource("/images/Warning.png");
    public static final URL INFO_IMAGE = CustomDialog.class.getResource("/images/Info.png");

    public static final String DELETE_CONFIRMATION = "Are you sure want to delete?";
    public static final String UPDATE_CONFIRMATION = "Are you sure want to update?";

    public CustomDialog(CustomDialogMessage dialogMessage, CustomConfirmDialog confirmDialog) {
        this.dialogMessage = dialogMessage;
        this.confirmDialog = confirmDialog;
    }

    private void getCustomDialog(String title, String text, URL url) {
        dialogMessage.showMessageDialog(title, text, url);
    }

    public void getSuccessCreatedMessageDialog(String title) {
        dialogMessage.showMessageDialog(SUCCESS, ConstantMessage.getSuccessCreatedMessage(title), SUCCESS_IMAGE);
    }

    public void getSuccessUpdateMessageDialog(String title) {
        dialogMessage.showMessageDialog(SUCCESS, ConstantMessage.getSuccessUpdateMessage(title), SUCCESS_IMAGE);
    }

    public void getSuccessDeletedMessageDialog(String title) {
        dialogMessage.showMessageDialog(SUCCESS, ConstantMessage.getSuccessDeleteMessage(title), SUCCESS_IMAGE);
    }

    public void getFailedMessageDialog(String message) {
        dialogMessage.showMessageDialog(FAIL, ConstantMessage.getInternalErrorMessage(message), FAIL_IMAGE);
    }

    private int getCustomConfirmDialog(String title, String text, URL url) {
        return confirmDialog.showConfirmDialog(title, text, url);
    }

    public int getConfirmDeleteDialog() {
        return confirmDialog.showConfirmDialog(WARNING, DELETE_CONFIRMATION, WARNING_IMAGE);
    }

    public int getConfirmUpdateDialog() {
        return confirmDialog.showConfirmDialog(INFO, UPDATE_CONFIRMATION, INFO_IMAGE);
    }
}
