package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.ConstantMessage;
import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.entity.Supplier;
import com.xyz.roemahduren.domain.model.request.SupplierProductRequest;
import com.xyz.roemahduren.domain.model.request.SupplierRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.model.response.SupplierProductResponse;
import com.xyz.roemahduren.domain.service.SupplierProductService;
import com.xyz.roemahduren.domain.service.SupplierService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.input.RoundedComboBox;
import com.xyz.roemahduren.presentation.component.table.TableActionCellEditor;
import com.xyz.roemahduren.presentation.component.table.TableActionCellRender;
import com.xyz.roemahduren.presentation.event.TableActionEvent;
import com.xyz.roemahduren.presentation.screen.SupplierScreen;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SupplierController {

    private final SupplierScreen supplierScreen;
    private final SupplierService supplierService;
    private final SupplierProductService supplierProductService;
    private final CustomDialog dialog;

    private DefaultTableModel supplierModel;
    private DefaultTableModel supplierProductModel;
    private List<Supplier> suppliers;
    private List<SupplierProductResponse> supplierProductResponses;
    private Supplier supplier;
    private SupplierProductResponse supplierProductResponse;

    public SupplierController(SupplierScreen supplierScreen, SupplierService supplierService, SupplierProductService supplierProductService, CustomDialog dialog) {
        this.supplierScreen = supplierScreen;
        this.supplierService = supplierService;
        this.supplierProductService = supplierProductService;
        this.dialog = dialog;

        initController();
        initSupplierTable();
        initSupplierProductForm();
        initSupplierProductTable();
    }

    private void initController() {
        supplierScreen.getSaveBtnSupplier().addActionListener(this::saveSupplier);
        supplierScreen.getSaveBtnProductSupplier().addActionListener(this::saveSupplierProduct);
        supplierScreen.getClearBtnSupplier().addActionListener(this::clearSupplierForm);
        supplierScreen.getClearBtnProductSupplier().addActionListener(this::clearSupplierProductForm);
    }

    private void initSupplierTable() {
        String[] HEADERS = {"#", "Nama Pemasok", "Alamat", "Aksi"};
        supplierModel = new DefaultTableModel(null, HEADERS);
        suppliers = supplierService.getAll();
        supplierScreen.getSupplierTable().setModel(supplierModel);

        if (suppliers.isEmpty()) {
            SwingUtil.setEmptyState(supplierScreen.getSupplierScrollPane());
        } else {
            supplierScreen.getSupplierScrollPane().setViewportView(supplierScreen.getSupplierTable());
        }

        int count = 0;
        for (Supplier s : suppliers) {
            supplierModel.addRow(new Object[] {
                    ++count,
                    s.getName(),
                    s.getAddress()
            });
        }

        TableActionEvent tableActionEvent = getTableActionSupplierEvent();
        supplierScreen.getSupplierTable().getColumnModel().getColumn(HEADERS.length - 1).setCellRenderer(new TableActionCellRender());
        supplierScreen.getSupplierTable().getColumnModel().getColumn(HEADERS.length - 1).setCellEditor(new TableActionCellEditor(tableActionEvent));
    }

    private void saveSupplier(ActionEvent actionEvent) {
        if (Objects.isNull(supplier)) {
            createNewSupplier();
            return;
        }

        if (dialog.getConfirmUpdateDialog() == 1) {
            updateSupplier();
        }
    }

    private void clearErrorMessage() {
        supplierScreen.getNameTextField().clearErrorMessage();
        supplierScreen.getAddressTextArea().clearErrorMessage();
        supplierScreen.getSupplierComboBox().clearErrorMessage();
        supplierScreen.getProductNameTextField().clearErrorMessage();
        supplierScreen.getPriceNumberFormattedField().clearErrorMessage();
        supplierScreen.getStockNumberFormattedField().clearErrorMessage();
    }

    private void createNewSupplier() {
        clearErrorMessage();
        new DatabaseWorker<>(
                () -> {
                    SwingUtil.setLoading(supplierScreen.getSaveBtnSupplier());
                    SupplierRequest supplierRequest = new SupplierRequest(supplierScreen.getNameTextField().getValue(), supplierScreen.getAddressTextArea().getValue());
                    ValidationUtil.validate(supplierRequest);
                    return supplierService.create(supplierRequest);
                },
                result -> {
                    dialog.getSuccessMessageDialog(ConstantMessage.SUPPLIER);
                    initSupplierTable();
                    initSupplierProductForm();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessages((ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> {
                    SwingUtil.clearSecondaryLoading(supplierScreen.getSaveBtnSupplier(), "Simpan");
                    clearSupplierForm();
                }
        ).execute();
    }

    private void updateSupplier() {
        clearErrorMessage();
        new DatabaseWorker<>(
                () -> {
                    SwingUtil.setLoading(supplierScreen.getSaveBtnSupplier());
                    SupplierRequest supplierRequest = new SupplierRequest(supplier.getId(), supplierScreen.getNameTextField().getValue(), supplierScreen.getAddressTextArea().getValue());
                    ValidationUtil.validate(supplierRequest);
                    return supplierService.update(supplierRequest);
                },
                result -> {
                    dialog.getSuccessUpdateMessageDialog(ConstantMessage.SUPPLIER);
                    initSupplierTable();
                    initSupplierProductForm();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessages((ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> {
                    SwingUtil.clearSecondaryLoading(supplierScreen.getSaveBtnSupplier(), "Simpan");
                    clearSupplierForm();
                }
        ).execute();
    }

    private void setErrorMessages(ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            String message = ValidationUtil.getMessage(validationModel.getMessages());

            if (validationModel.getPath().equalsIgnoreCase("name")) {
                supplierScreen.getProductNameTextField().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("address")) {
                supplierScreen.getAddressTextArea().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("supplier")) {
                supplierScreen.getSupplierComboBox().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("productName")) {
                supplierScreen.getProductNameTextField().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("price")) {
                supplierScreen.getPriceNumberFormattedField().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("stock")) {
                supplierScreen.getStockNumberFormattedField().setErrorMessage(message);
            }
        }
    }

    private TableActionEvent getTableActionSupplierEvent() {
        return new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                setFormSupplier(row);
            }

            @Override
            public void onDelete(int row) {
                int confirmDeleteDialog = dialog.getConfirmDeleteDialog();
                if (confirmDeleteDialog != 1) return;
                new DatabaseWorker<>(
                        () -> {
                            Supplier supplier = suppliers.get(row);
                            supplierService.deleteById(supplier.getId());
                            return true;
                        },
                        result -> {
                            initSupplierTable();
                            dialog.getSuccessDeletedMessageDialog(ConstantMessage.SUPPLIER);
                            initSupplierProductForm();
                        },
                        throwable -> dialog.getFailedMessageDialog(throwable.getMessage()),
                        () -> {}
                ).execute();
            }
        };
    }

    private void setFormSupplier(int row) {
        supplier = suppliers.get(row);
        supplierScreen.getNameTextField().setValue(supplier.getName());
        supplierScreen.getAddressTextArea().setValue(supplier.getAddress());
        supplierScreen.getSaveBtnSupplier().setText("Ubah");
    }

    private void clearSupplierForm(ActionEvent actionEvent) {
        clearSupplierForm();
    }

    private void clearSupplierForm() {
        supplierScreen.getSaveBtnSupplier().setText("Simpan");
        supplierScreen.getNameTextField().setValue("");
        supplierScreen.getAddressTextArea().setValue("");
    }

    private void initSupplierProductTable() {
        String[] HEADERS = {"#", "Nama Pemasok", "Nama Produk", "Harga", "Stok", "Aksi"};
        supplierProductModel = new DefaultTableModel(null, HEADERS);
        supplierScreen.getProductSupplierTable().setModel(supplierProductModel);

        supplierProductResponses = supplierProductService.getAll();

        if (supplierProductResponses.isEmpty()) {
            SwingUtil.setEmptyState(supplierScreen.getProductSupplierScrollPane());
        } else {
            supplierScreen.getProductSupplierScrollPane().setViewportView(supplierScreen.getProductSupplierTable());
        }

        int count = 0;
        for (SupplierProductResponse sp : supplierProductResponses) {
            supplierProductModel.addRow(new Object[] {
                    ++count,
                    sp.getSupplierName(),
                    sp.getProductName(),
                    sp.getPrice(),
                    sp.getStock()
            });
        }

        TableActionEvent tableActionEvent = getTableActionSupplierProductEvent();
        supplierScreen.getProductSupplierTable().getColumnModel().getColumn(HEADERS.length - 1).setCellRenderer(new TableActionCellRender());
        supplierScreen.getProductSupplierTable().getColumnModel().getColumn(HEADERS.length - 1).setCellEditor(new TableActionCellEditor(tableActionEvent));
    }

    private TableActionEvent getTableActionSupplierProductEvent() {
        return new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                setFormSupplierProduct(row);
            }

            @Override
            public void onDelete(int row) {
                if (dialog.getConfirmDeleteDialog() != 1) return;
                new DatabaseWorker<>(
                        () -> {
                            SupplierProductResponse sp = supplierProductResponses.get(row);
                            supplierProductService.deleteById(sp.getId());
                            return true;
                        },
                        result -> {
                            initSupplierProductTable();
                            clearSupplierProductForm();
                            dialog.getSuccessDeletedMessageDialog(ConstantMessage.SUPPLIER_PRODUCT);
                        },
                        throwable -> {
                            if (throwable instanceof ValidationException) {
                                setErrorMessages((ValidationException) throwable);
                                return;
                            }
                            dialog.getFailedMessageDialog(throwable.getMessage());
                        },
                        () -> {}
                ).execute();
            }
        };
    }

    private void clearSupplierProductForm(ActionEvent actionEvent) {
        clearSupplierProductForm();
    }

    private void clearSupplierProductForm() {
        supplierScreen.getSupplierComboBox().getComboBox().setSelectedIndex(0);
        supplierScreen.getProductNameTextField().setValue("");
        supplierScreen.getPriceNumberFormattedField().setValue("0");
        supplierScreen.getStockNumberFormattedField().setValue("0");
        supplierScreen.getSaveBtnProductSupplier().setText("Simpan");
    }

    private void saveSupplierProduct(ActionEvent actionEvent) {
        if (Objects.isNull(supplierProductResponse)) {
            createNewSupplierProduct();
            return;
        }

        if (dialog.getConfirmUpdateDialog() == 1) {
            updateSupplierProduct();
        }
    }

    private void createNewSupplierProduct() {
        new DatabaseWorker<>(
                () -> {
                    int selectedIndex = supplierScreen.getSupplierComboBox().getComboBox().getSelectedIndex() - 1;
                    Supplier supplier = suppliers.get(selectedIndex);
                    SupplierProductRequest supplierProductRequest = new SupplierProductRequest(
                            supplier.getId(),
                            supplierScreen.getProductNameTextField().getValue(),
                            Long.valueOf(supplierScreen.getPriceNumberFormattedField().getValue()),
                            Integer.valueOf(supplierScreen.getStockNumberFormattedField().getValue())
                    );
                    ValidationUtil.validate(supplierProductRequest);
                    return supplierProductService.create(supplierProductRequest);
                },
                result -> {
                    clearSupplierProductForm();
                    initSupplierProductTable();
                    dialog.getSuccessCreatedMessageDialog(ConstantMessage.SUPPLIER_PRODUCT);
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessages((ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> SwingUtil.clearSecondaryLoading(supplierScreen.getSaveBtnProductSupplier(), "Simpan")
        ).execute();
    }

    private void updateSupplierProduct() {
        new DatabaseWorker<>(
                () -> {
                    int selectedIndex = supplierScreen.getSupplierComboBox().getComboBox().getSelectedIndex() - 1;
                    Supplier supplier = suppliers.get(selectedIndex);
                    SupplierProductRequest supplierProductRequest = new SupplierProductRequest(
                            supplierProductResponse.getId(),
                            supplier.getId(),
                            supplierScreen.getProductNameTextField().getValue(),
                            Long.valueOf(supplierScreen.getPriceNumberFormattedField().getValue()),
                            Integer.valueOf(supplierScreen.getStockNumberFormattedField().getValue())
                    );
                    ValidationUtil.validate(supplierProductRequest);
                    return supplierProductService.update(supplierProductRequest);
                },
                result -> {
                    clearSupplierProductForm();
                    initSupplierProductTable();
                    dialog.getSuccessUpdateMessageDialog(ConstantMessage.SUPPLIER_PRODUCT);
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessages((ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> SwingUtil.clearSecondaryLoading(supplierScreen.getSaveBtnProductSupplier(), "Simpan")
        ).execute();
    }

    private void setFormSupplierProduct(int row) {
        supplierProductResponse = supplierProductResponses.get(row);
        supplierScreen.getSaveBtnProductSupplier().setText("Ubah");
        supplierScreen.getSupplierComboBox().getComboBox().setSelectedItem(supplierProductResponse.getSupplierName());
        supplierScreen.getProductNameTextField().setValue(supplierProductResponse.getProductName());
        supplierScreen.getPriceNumberFormattedField().setValue(supplierProductResponse.getPrice().toString());
        supplierScreen.getStockNumberFormattedField().setValue(supplierProductResponse.getStock().toString());
    }

    private void initSupplierProductForm() {
        RoundedComboBox<String> comboBox = supplierScreen.getSupplierComboBox().getComboBox();
        comboBox.removeAllItems();
        comboBox.addItem("--Pilih Supplier--");
        suppliers = supplierService.getAll();

        if (!suppliers.isEmpty()) {
            for (Supplier s : suppliers) {
                comboBox.addItem(s.getName());
            }
        }
    }

    public SupplierScreen getSupplierScreen() {
        initSupplierProductForm();
        return supplierScreen;
    }
}
