package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.entity.Category;
import com.xyz.roemahduren.domain.entity.Product;
import com.xyz.roemahduren.domain.model.request.ProductPriceRequest;
import com.xyz.roemahduren.domain.model.request.ProductRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.model.response.ProductResponse;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.domain.service.CategoryService;
import com.xyz.roemahduren.domain.service.ProductService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.table.PanelAction;
import com.xyz.roemahduren.presentation.component.table.TableActionCellEditor;
import com.xyz.roemahduren.presentation.component.table.TableActionCellRender;
import com.xyz.roemahduren.presentation.event.TableActionEvent;
import com.xyz.roemahduren.presentation.screen.ProductScreen;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.xyz.roemahduren.constant.ConstantMessage.PRODUCT;

public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BranchService branchService;
    private final ProductScreen productScreen;
    private final CustomDialog dialog;

    private DefaultTableModel model;
    private List<ProductResponse> products;
    private ProductResponse productResponse;
    private Product product;
    private List<Category> categories;
    private List<Branch> branches;

    public ProductController(ProductService productService, CategoryService categoryService, BranchService branchService, ProductScreen productScreen, CustomDialog dialog) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.branchService = branchService;
        this.productScreen = productScreen;
        this.dialog = dialog;
        initController();
    }

    private void initForm() {
        categories = categoryService.getAll();
        productScreen.getCategoryComboBox().getComboBox().removeAllItems();

        for (Category category : categories) {
            productScreen.getCategoryComboBox().getComboBox().addItem(category.getName());
        }

        branches = branchService.getAll();
        productScreen.getBranchComboBox().getComboBox().removeAllItems();

        List<Branch> branches = branchService.getAll();
        for (Branch branch : branches) {
            productScreen.getBranchComboBox().getComboBox().addItem(branch.getName());
        }
    }

    private void initController() {
        productScreen.getSaveBtn().addActionListener(this::saveProduct);
        productScreen.getClearBtn().addActionListener(this::clearForm);
    }

    private void clearForm(ActionEvent actionEvent) {
        clearForm();
    }

    private void clearForm() {
        productScreen.getNameTextField().setValue("");
        productScreen.getPriceNumberFormattedField().setValue("");
        productScreen.getCategoryComboBox().getComboBox().setSelectedIndex(0);
        productScreen.getStockNumberFormattedField().setValue("");
        productScreen.getBranchComboBox().getComboBox().setSelectedIndex(0);
        productScreen.getIsActiveCheckbox().getCheckbox().setSelected(false);
        productScreen.getIsActiveCheckbox().setVisible(false);
        productResponse = null;
        productScreen.getSaveBtn().setText("Simpan");
    }

    private void saveProduct(ActionEvent actionEvent) {
        new DatabaseWorker<>(
                () -> {
                    int selectedIndexCategory = productScreen.getCategoryComboBox().getComboBox().getSelectedIndex();
                    Category category = categories.get(selectedIndexCategory);
                    int selectedIndexBranch = productScreen.getBranchComboBox().getComboBox().getSelectedIndex();
                    Branch branch = branches.get(selectedIndexBranch);


                    ProductRequest productRequest = new ProductRequest(
                            productScreen.getNameTextField().getValue(),
                            category.getId(),
                            new ProductPriceRequest(
                                    new BigDecimal(productScreen.getPriceNumberFormattedField().getValue()),
                                    Integer.parseInt(productScreen.getStockNumberFormattedField().getValue()),
                                    "",
                                    branch.getId()
                            )
                    );
                    ValidationUtil.validate(productRequest);
                    ValidationUtil.validate(productRequest.getProductPriceRequest());
                    return productService.create(productRequest);
                },
                productResponse -> {
                    dialog.getSuccessCreatedMessageDialog(PRODUCT);
                    initTable();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        Set<ErrorValidationModel> validationModels = ((ValidationException) throwable).getValidationModels();
                        for (ErrorValidationModel validationModel : validationModels) {
                            Set<String> messages = validationModel.getMessages();
                            String message = ValidationUtil.getMessage(messages);
                            System.out.println(message);
                        }
                        return;
                    }
                    throwable.printStackTrace();
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                this::clearForm
        ).execute();
    }

    public ProductScreen getProductScreen() {
        initForm();
        initTable();
        return productScreen;
    }

    public void initTable() {
        final String[] HEADERS = {"#", "Nama Produk", "Harga", "Stok", "Cabang", "Aktif", "Aksi"};
        model = new DefaultTableModel(null, HEADERS);

        products = productService.getAll();

        int counter = 0;
        for (ProductResponse product : products) {
            model.addRow(new Object[]{++counter, product.getName(), product.getPrice(), product.getStock(), product.getBranch(), product.getValidString()});
        }

        productScreen.getProductTable().setModel(model);
        productScreen.getScrollTable().setViewportView(productScreen.getProductTable());

        TableActionEvent tableActionEvent = getTableActionEvent();
        TableActionCellRender tableActionCellRender = new TableActionCellRender();
        PanelAction panelAction = tableActionCellRender.getPanelAction();
        String nonactive = "Nonaktif";
        panelAction.getDeleteButton().setText(nonactive);
        productScreen.getProductTable().getColumnModel().getColumn(HEADERS.length - 1).setCellRenderer(tableActionCellRender);
        productScreen.getProductTable().getColumnModel().getColumn(HEADERS.length - 1).setCellEditor(new TableActionCellEditor(tableActionEvent, null, nonactive));
    }

    private TableActionEvent getTableActionEvent() {
        return new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                setForm(row);
            }

            @Override
            public void onDelete(int row) {
                int confirmDeleteDialog = dialog.getConfirmDeleteDialog();
                if (confirmDeleteDialog != 1) return;
                new DatabaseWorker<>(
                        () -> {
                            ProductResponse productResponse = products.get(row);
                            productService.deleteById(productResponse.getProductId());
                            return true;
                        },
                        o -> {
                            dialog.getSuccessUpdateMessageDialog(PRODUCT);
                            initTable();
                        },
                        throwable -> dialog.getFailedMessageDialog(throwable.getMessage()),
                        () -> {
                        }
                ).execute();
            }
        };
    }

    private void setForm(int row) {
        ProductResponse productResponse = products.get(row);
        productScreen.getIsActiveCheckbox().setVisible(true);
        productScreen.getSaveBtn().setText("Ubah");
        productScreen.getNameTextField().setValue(productResponse.getName());
        productScreen.getPriceNumberFormattedField().setValue(String.valueOf(productResponse.getPrice().intValue()));
        productScreen.getCategoryComboBox().getComboBox().setSelectedItem(productResponse.getCategory());
        productScreen.getStockNumberFormattedField().setValue(productResponse.getStock().toString());
        productScreen.getBranchComboBox().getComboBox().setSelectedItem(productResponse.getBranch());
        productScreen.getIsActiveCheckbox().getCheckbox().setSelected(productResponse.getValid());

        product = new Product(productResponse.getProductId(), productResponse.getName(), "", productResponse.getValid());
        Optional<Category> category = categoryService.getAllByName(productResponse.getCategory()).stream().findFirst();
        category.ifPresent(value -> product.setCategoryId(value.getId()));
    }
}
