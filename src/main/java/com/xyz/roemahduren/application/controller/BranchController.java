package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.model.request.BranchRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.event.TableActionEvent;
import com.xyz.roemahduren.presentation.screen.BranchScreen;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Set;

import static com.xyz.roemahduren.constant.ConstantMessage.*;

public class BranchController {

    private final BranchService branchService;
    private final BranchScreen branchScreen;
    private final CustomDialog dialog;

    private List<Branch> branches;
    private Branch branch;

    public BranchController(BranchService branchService, BranchScreen branchScreen, CustomDialog dialog) {
        this.branchService = branchService;
        this.branchScreen = branchScreen;
        this.dialog = dialog;
        initTable();
        initController();
    }

    public void initController() {
        branchScreen.getSaveBtn().addActionListener(this::saveBranch);
        branchScreen.getClearBtn().addActionListener(this::clearForm);
    }

    private void clearForm(ActionEvent actionEvent) {
        clearForm();
    }

    private void saveBranch(ActionEvent actionEvent) {
        branchScreen.getAddressTextArea().clearErrorMessage();
        branchScreen.getNameTextField().clearErrorMessage();

        if (branch == null) {
            createNewBranch();
            return;
        }

        int confirmUpdateDialog = dialog.getConfirmUpdateDialog();
        if (confirmUpdateDialog != 1) return;
        updateBranch();
    }

    private void updateBranch() {
        RoundedButton saveBtn = branchScreen.getSaveBtn();
        new DatabaseWorker<>(
                () -> {
                    SwingUtil.setLoading(saveBtn);
                    BranchRequest branchRequest = new BranchRequest(
                            branch.getId(),
                            branchScreen.getNameTextField().getValue(),
                            branchScreen.getAddressTextArea().getValue()
                    );
                    ValidationUtil.validate(branchRequest);
                    return branchService.update(branchRequest);
                },
                branch -> {
                    dialog.getSuccessUpdateMessageDialog(BRANCH);
                    initTable();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessages((ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> {
                    clearForm();
                    SwingUtil.clearLoading(branchScreen.getSaveBtn(), saveBtn.getText());
                    branch = null;
                }
        ).execute();
    }

    private void createNewBranch() {
        RoundedButton saveBtn = branchScreen.getSaveBtn();
        new DatabaseWorker<>(
                () -> {
                    SwingUtil.setLoading(saveBtn);
                    BranchRequest branchRequest = new BranchRequest(
                            branchScreen.getNameTextField().getValue(),
                            branchScreen.getAddressTextArea().getValue()
                    );
                    ValidationUtil.validate(branchRequest);
                    return branchService.create(branchRequest);
                },
                branch -> {
                    dialog.getSuccessCreatedMessageDialog(BRANCH);
                    initTable();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessages((ValidationException) throwable);
                        return;
                    }

                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> {
                    SwingUtil.clearLoading(saveBtn, saveBtn.getText());
                    clearForm();
                    branch = null;
                }
        ).execute();
    }

    private void setErrorMessages(ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            Set<String> messages = validationModel.getMessages();
            String htmlMessage = ValidationUtil.getHtmlMessage(messages, branchScreen.getNameTextField().getPreferredSize().width);

            if (validationModel.getPath().equals("name")) {
                branchScreen.getNameTextField().setErrorMessage(htmlMessage);
            } else {
                branchScreen.getAddressTextArea().setErrorMessage(htmlMessage);
            }
        }
    }

    public void initTable() {
        final String[] COLUMN = {"#", "Nama Cabang", "Alamat Cabang", "Aksi"};
        DefaultTableModel model = new DefaultTableModel(null, COLUMN);

        branches = branchService.getAll();

        if (branches.isEmpty()) {
            SwingUtil.setEmptyState(branchScreen.getScrollTable());
        } else {
            branchScreen.getScrollTable().setViewportView(branchScreen.getBranchTable());
        }


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
                setForm(row);
            }

            @Override
            public void onDelete(int row) {
                deleteProduct(row);
            }
        };
    }

    private void setForm(int row) {
        branch = branches.get(row);
        branchScreen.getNameTextField().setValue(branch.getName());
        branchScreen.getAddressTextArea().setValue(branch.getAddress());
        branchScreen.getSaveBtn().setText(BTN_TEXT_UPDATE);
    }

    private void deleteProduct(int row) {
        int info = dialog.getConfirmDeleteDialog();

        if (info == 1) {
            new DatabaseWorker<>(
                    () -> {
                        Branch branch = branches.get(row);
                        branchService.deleteById(branch.getId());
                        return branch.getId() != null;
                    },
                    branch -> {
                        initTable();
                        dialog.getSuccessDeletedMessageDialog(BRANCH);
                    },
                    throwable -> dialog.getFailedMessageDialog(throwable.getMessage()),
                    () -> {
                    }
            ).execute();
        }
    }

    private void clearForm() {
        branchScreen.getNameTextField().setValue("");
        branchScreen.getAddressTextArea().setValue("");
        branchScreen.getSaveBtn().setText(BTN_TEXT_SAVE);
        branch = null;
    }

    public BranchScreen getBranchScreen() {
        return branchScreen;
    }
}
