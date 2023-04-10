package com.xyz.roemahduren.application.controller.branch;

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
import java.util.stream.Collectors;

public class BranchController {
    private final BranchService branchService;
    private BranchModel branchModel;
    private List<Branch> branches;
    private BranchScreen branchScreen;

    public BranchController(BranchScreen branchScreen, BranchService branchService) {
        this.branchScreen = branchScreen;
        this.branchService = branchService;
        this.branches = loadBranches();
        this.branchModel = new BranchModel();

        initController();

        this.branchScreen.getTable1().setModel(branchModel);
    }

    private void initController() {
        branchScreen.getBtnSave().addActionListener(this::saveBranch);
        branchScreen.getSearchBtn().addActionListener(this::searchBranch);
    }

    private void searchBranch(ActionEvent actionEvent) {
        if (branchScreen.getTfSearch().getText().length() == 0) {
            branches = loadBranches();
            branchModel.fireTableDataChanged();
            return;
        }

        List<Branch> list = filterBranch(branchScreen.getTfSearch().getText());
        if (!list.isEmpty()) {
            branches = list;
            branchScreen.getScrollBranchTable().setViewportView(branchScreen.getTable1());
            branchModel.fireTableDataChanged();
        } else {
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel("Data is Empty"));
            branchScreen.getScrollBranchTable().setViewportView(jPanel);
        }
    }

    private void saveBranch(ActionEvent actionEvent) {
        clearErrorMessages();
        String btnText = branchScreen.getBtnSave().getText();

        DatabaseWorker<Branch> worker = new DatabaseWorker<>(
                () -> {
                    setLoading();
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
                        setErrorMessage((ValidationException) throwable);
                    } else {
                        JOptionPane.showMessageDialog(null, throwable.getMessage());
                    }
                },
                () -> clearLoading(btnText)
        );
        worker.execute();
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

    private List<Branch> filterBranch(String name) {
        return branches.stream().filter(branch -> branch.getName().equals(name)).collect(Collectors.toList());
    }

    public BranchScreen getBranchScreen() {
        return branchScreen;
    }


    public class BranchModel extends AbstractTableModel {
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
        public String getColumnName(int column) {
            return HEADERS[column];
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
