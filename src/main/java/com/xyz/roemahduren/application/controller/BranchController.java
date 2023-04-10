package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.model.request.BranchRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.screen.BranchScreen;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BranchController {
    private final BranchScreen screen;
    private final BranchService branchService;
    private BranchModel branchModel;
    private List<Branch> branches;

    public BranchController(BranchScreen screen, BranchService branchService) {
        this.screen = screen;
        this.branchService = branchService;
        branches = new ArrayList<>();

        loadBranches();
    }

    private void initController() {
        screen.getBtnSave().addActionListener(this::saveBranch);
    }

    private void saveBranch(ActionEvent actionEvent) {
        BranchScreen branchScreen = screen;
        String btnText = screen.getBtnSave().getText();

        DatabaseWorker<Branch> worker = new DatabaseWorker<>(
                () -> {
                    branchScreen.getBtnSave().setText("Loading...");
                    branchScreen.getBtnSave().setBackground(new Color(0xF4F4F4));
                    branchScreen.getBtnSave().setBorderColor(new Color(0xF4F4F4));
                    branchScreen.getBtnSave().setEnabled(false);

                    BranchRequest branchRequest = new BranchRequest(
                            branchScreen.getTfBranchName().getText(),
                            branchScreen.getTaAddress().getText()
                    );
                    ValidationUtil.validate(branchRequest);

                    Branch branch = branchService.create(branchRequest);
                    branches.add(branch);
                    branchModel.fireTableDataChanged();
                    return branch;
                },
                branch -> JOptionPane.showMessageDialog(null, "Success Save Branch"),
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        Set<ErrorValidationModel> validationModels = ((ValidationException) throwable).getValidationModels();
                        for (ErrorValidationModel validationModel : validationModels) {
                            String htmlMessage = ValidationUtil.getHtmlMessage(validationModel.getMessages(), branchScreen.getTfBranchName().getPreferredSize().width);

                            if (validationModel.getPath().equals("name")) {
                                branchScreen.getErrorBranchNameLabel().setText(htmlMessage);
                            } else {
                                branchScreen.getErrorAddressLabel().setText(htmlMessage);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, throwable.getMessage());
                    }
                },
                () -> {
                    branchScreen.getBtnSave().setText(btnText);
                    branchScreen.getBtnSave().setEnabled(true);
                    branchScreen.getBtnSave().setBackground(new Color(0xF6921E));
                    branchScreen.getBtnSave().setBorderColor(new Color(0xF6921E));
                }
        );
        worker.execute();
    }

    private void loadBranches() {
        List<Branch> branchList = branchService.getAll();
        if (!branchList.isEmpty()) {
            branches = branchList;
        }
    }

    public BranchScreen getScreen() {
        return screen;
    }

    private class BranchModel extends AbstractTableModel {
        protected final String[] HEADERS = {"#", "Branch Name", "Address"};

        @Override
        public int getRowCount() {
            return branches.size();
        }

        @Override
        public int getColumnCount() {
            return HEADERS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return branches.get(rowIndex).getId();
                case 1:
                    return branches.get(rowIndex).getName();
                case 2:
                    return branches.get(rowIndex).getAddress();
                default:
                    return "-";
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null) {
                return getValueAt(0, columnIndex).getClass();
            } else {
                return Object.class;
            }
        }
    }

}
