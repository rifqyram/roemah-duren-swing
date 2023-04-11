package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.model.request.BranchRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.DataEmpty;
import com.xyz.roemahduren.presentation.component.table.Table;
import com.xyz.roemahduren.presentation.component.table.TableActionCellEditor;
import com.xyz.roemahduren.presentation.component.table.TableActionCellRender;
import com.xyz.roemahduren.presentation.event.TableActionEvent;
import com.xyz.roemahduren.presentation.screen.BranchScreen;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BranchController {
    private final BranchService branchService;
    private final BranchScreen branchScreen;
    private DefaultTableModel branchModel;
    private List<Branch> branches;
    private int row;


    public BranchController(BranchScreen branchScreen, BranchService branchService) {
        this.branchScreen = branchScreen;
        this.branchService = branchService;
        this.branches = loadBranches();
        initController();
        initTableModel();
    }

    private void initTableModel() {
        String[] headers = {"#", "Branch Name", "Address", "Action"};
        this.branchModel = new DefaultTableModel(null, headers);

        if (this.branches.isEmpty()) {
            setEmptyState();
        }

        refreshTable();

        TableActionEvent tableActionEvent = getTableActionEvent(branchScreen, branchService);
        this.branchScreen.getBranchTable().setModel(branchModel);
        this.branchScreen.getBranchTable().getColumnModel().getColumn(branchScreen.getBranchTable().getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
        this.branchScreen.getBranchTable().getColumnModel().getColumn(branchScreen.getBranchTable().getColumnCount() - 1).setCellEditor(new TableActionCellEditor(tableActionEvent));
    }

    private TableActionEvent getTableActionEvent(BranchScreen branchScreen, BranchService branchService) {
        return new TableActionEvent() {
            @Override
            public void onEdit(int param) {
                if (branchScreen.getBranchTable().isEditing()) {
                    branchScreen.getBranchTable().getCellEditor().stopCellEditing();
                }

                Branch branch = branches.get(param);
                if (branch != null) {
                    branchScreen.getBtnSave().setText("Update");
                    setForm(branch.getName(), branch.getAddress());
                    row = param;
                }
            }

            @Override
            public void onDelete(int row) {
                if (branchScreen.getBranchTable().isEditing()) {
                    branchScreen.getBranchTable().getCellEditor().stopCellEditing();
                }

                int i = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

                if (i != 0) return;
                DatabaseWorker<Boolean> worker = new DatabaseWorker<>(
                        () -> {
                            Branch branch = branches.get(row);
                            branches.remove(branch);
                            branchService.deleteById(branch.getId());
                            refreshTable();

                            if (branches.isEmpty()) {
                                setEmptyState();
                            }

                            return true;
                        },
                        response -> JOptionPane.showMessageDialog(null, "Success Delete Branch"),
                        throwable -> JOptionPane.showMessageDialog(null, throwable.getMessage()),
                        () -> {
                        }
                );
                worker.execute();
            }
        };
    }

    private void initController() {
        branchScreen.getBtnSave().addActionListener(this::saveBranch);
        branchScreen.getSearchBtn().addActionListener(this::searchBranch);
        branchScreen.getBtnCancel().addActionListener(this::clear);
    }

    private void clear(ActionEvent actionEvent) {
        clearForm();
    }

    private void searchBranch(ActionEvent actionEvent) {
        if (branchScreen.getTfSearch().getText().isEmpty()) {
            branches = loadBranches();
            refreshTable();
            return;
        }


        List<Branch> branchesFound = branchService.getByName(branchScreen.getTfSearch().getText());

        if (branchesFound.isEmpty()) {
            setEmptyState();
        } else {
            setTableView();
            branches = branchesFound;
        }
        refreshTable();
    }

    private void setTableView() {
        JTable table = branchScreen.getBranchTable();
        branchScreen.getScrollBranchTable().setViewportView(table);
    }

    private void setEmptyState() {
        DataEmpty dataEmpty = new DataEmpty();
        branchScreen.getScrollBranchTable().setViewportView(dataEmpty);
    }

    private void refreshTable() {
        branchModel.setRowCount(0);

        int index = 0;
        for (Branch branch : branches) {
            branchModel.addRow(new Object[]{++index, branch.getName(), branch.getAddress()});
        }
    }

    private void saveBranch(ActionEvent actionEvent) {
        clearErrorMessages();
        String btnText = branchScreen.getBtnSave().getText();

        if (btnText.equals("Save")) {
            save(btnText);
        } else {
            update(btnText);
        }
    }

    private void update(String btnText) {
        new DatabaseWorker<>(
                () -> {
                    setLoading();
                    Branch currentBranch = branches.get(row);

                    BranchRequest branchRequest = new BranchRequest(
                            currentBranch.getId(),
                            branchScreen.getTfBranchName().getText(),
                            branchScreen.getTaAddress().getText()
                    );
                    ValidationUtil.validate(branchRequest);

                    Branch newBranch = branchService.update(branchRequest);
                    branches.set(row, newBranch);
                    refreshTable();

                    clearForm();
                    return newBranch;
                },
                branch -> JOptionPane.showMessageDialog(null, "Success Save Branch"),
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessage((ValidationException) throwable);
                    } else {
                        JOptionPane.showMessageDialog(null, throwable.getMessage());
                    }
                },
                () -> clearLoading(btnText)
        ).execute();
    }

    private void save(String btnText) {
        new DatabaseWorker<>(
                () -> {
                    setLoading();
                    BranchRequest branchRequest = new BranchRequest(
                            branchScreen.getTfBranchName().getText(),
                            branchScreen.getTaAddress().getText()
                    );
                    ValidationUtil.validate(branchRequest);

                    Branch branch = branchService.create(branchRequest);
                    branches.add(branch);
                    refreshTable();

                    if (!branches.isEmpty()) {
                        setTableView();
                    }

                    clearForm();
                    return branch;
                },
                branch -> JOptionPane.showMessageDialog(null, "Success Save Branch"),
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessage((ValidationException) throwable);
                    } else {
                        JOptionPane.showMessageDialog(null, throwable.getMessage());
                    }
                },
                () -> clearLoading(btnText)
        ).execute();
    }

    private void clearForm() {
        branchScreen.getTfBranchName().setText("");
        branchScreen.getTaAddress().setText("");
        branchScreen.getBtnSave().setText("Save");
    }

    public void setForm(String name, String address) {
        branchScreen.getTfBranchName().setText(name);
        branchScreen.getTaAddress().setText(address);
    }

    private void clearErrorMessages() {
        branchScreen.getErrorBranchNameLabel().setText("");
        branchScreen.getErrorAddressLabel().setText("");
    }

    private void setLoading() {
        branchScreen.getBtnSave().setText("Loading...");
        branchScreen.getBtnSave().setBackground(new Color(0xF4F4F4));
        branchScreen.getBtnSave().setBorderColor(new Color(0xF4F4F4));
        branchScreen.getBtnSave().setEnabled(false);
    }

    private void clearLoading(String btnText) {
        branchScreen.getBtnSave().setText(btnText);
        branchScreen.getBtnSave().setEnabled(true);
        branchScreen.getBtnSave().setBackground(new Color(0x3DA9FC));
        branchScreen.getBtnSave().setBorderColor(new Color(0x3DA9FC));
    }

    private void setErrorMessage(ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            String htmlMessage = ValidationUtil.getHtmlMessage(validationModel.getMessages(), branchScreen.getTfBranchName().getPreferredSize().width);

            if (validationModel.getPath().equals("name")) {
                branchScreen.getErrorBranchNameLabel().setText(htmlMessage);
                branchScreen.getTfBranchName().requestFocus();
            } else {
                branchScreen.getErrorAddressLabel().setText(htmlMessage);
                branchScreen.getTaAddress().requestFocus();
            }
        }
    }

    private List<Branch> loadBranches() {
        List<Branch> branchList = branchService.getAll();
        if (!branchList.isEmpty()) {
            return branches = branchList;
        }
        return new ArrayList<>();
    }

    public BranchScreen getBranchScreen() {
        return branchScreen;
    }

}
