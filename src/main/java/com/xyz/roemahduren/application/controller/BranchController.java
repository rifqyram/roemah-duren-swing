package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.NotificationResource;
import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.model.request.BranchRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.CustomConfirmDialog;
import com.xyz.roemahduren.presentation.component.CustomDialogMessage;
import com.xyz.roemahduren.presentation.event.TableActionEvent;
import com.xyz.roemahduren.presentation.screen.BranchScreen;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class BranchController {

    private final BranchService branchService;
    private final BranchScreen branchScreen;

    private List<Branch> branches;
    private DefaultTableModel model;
    private CustomConfirmDialog confirmDialog;
    private CustomDialogMessage notification;

    public BranchController(BranchService branchService, BranchScreen branchScreen) {
        this.branchService = branchService;
        this.branchScreen = branchScreen;
        confirmDialog = new CustomConfirmDialog();
        notification = new CustomDialogMessage();
        initTable();
        initController();
    }

    public void initController() {
        branchScreen.getSaveBtn().addActionListener(this::saveBranch);
    }

    private void saveBranch(ActionEvent actionEvent) {
        branchScreen.getAddressTextArea().clearErrorMessage();
        branchScreen.getNameTextField().clearErrorMessage();

        new DatabaseWorker<>(
                () -> {
                    BranchRequest branchRequest = new BranchRequest(
                            branchScreen.getNameTextField().getValue(),
                            branchScreen.getAddressTextArea().getValue()
                    );
                    ValidationUtil.validate(branchRequest);
                    return branchService.create(branchRequest);
                },
                branch -> {
                    if (branch != null) {
                        getNotification(NotificationResource.SUCCESS, "Successfully create new branch", NotificationResource.SUCCESS_IMAGE);
                        initTable();
                    }
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        Set<ErrorValidationModel> validationModels = ((ValidationException) throwable).getValidationModels();
                        for (ErrorValidationModel validationModel : validationModels) {
                            Set<String> messages = validationModel.getMessages();
                            String htmlMessage = ValidationUtil.getHtmlMessage(messages, branchScreen.getNameTextField().getPreferredSize().width);

                            if (validationModel.getPath().equals("name")) {
                                branchScreen.getNameTextField().setErrorMessage(htmlMessage);
                            } else {
                                branchScreen.getAddressTextArea().setErrorMessage(htmlMessage);
                            }
                        }
                        return;
                    }

                    getNotification(NotificationResource.FAIL, throwable.getMessage(), NotificationResource.FAIL_IMAGE);
                },
                this::clearForm
        ).execute();
    }

    public void initTable() {
        final String[] COLUMN = {"#", "Branch Name", "Address", "Action"};
        model = new DefaultTableModel(null, COLUMN);

        branches = branchService.getAll();

        int counter = 0;
        for (Branch branch : branches) {
            model.addRow(new Object[]{++counter, branch.getName(), branch.getAddress()});
        }

        branchScreen.getBranchTable().setModel(model);
        TableActionEvent tableActionEvent = getTableActionEvent();
        SwingUtil.setActionTable(branchScreen.getBranchTable(), COLUMN, tableActionEvent);
    }

    private TableActionEvent getTableActionEvent() {
        return new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println(row);
            }

            @Override
            public void onDelete(int row) {
                deleteProduct(row);
            }
        };
    }

    private void deleteProduct(int row) {
        int info = confirmDialog.showConfirmDialog("Info", "Are you sure want to delete?", null);
        if (info == 1) {
            new DatabaseWorker<>(
                    () -> {
                        Branch branch = branches.get(row);
                        branchService.deleteById(branch.getId());
                        return branch.getId() != null;
                    },
                    branch -> {
                        if (branch) {
                            initTable();
                            getNotification(NotificationResource.SUCCESS, "Successfully delete branch", NotificationResource.SUCCESS_IMAGE);
                        } else {
                            getNotification(NotificationResource.FAIL, "Failed delete branch", NotificationResource.FAIL_IMAGE);
                        }
                    },
                    throwable -> {
                        getNotification(NotificationResource.FAIL, throwable.getMessage(), NotificationResource.FAIL_IMAGE);
                    },
                    () -> {
                    }
            ).execute();
        }
    }

    private void getNotification(String title, String text, URL url) {
        notification.showMessageDialog(title, text, url);
    }

    private void clearForm() {
        branchScreen.getNameTextField().setValue("");
        branchScreen.getAddressTextArea().setValue("");
    }

    public BranchScreen getBranchScreen() {
        return branchScreen;
    }
}
