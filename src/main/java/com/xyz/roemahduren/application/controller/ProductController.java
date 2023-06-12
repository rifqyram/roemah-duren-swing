package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.ConstantMessage;
import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.entity.Category;
import com.xyz.roemahduren.domain.entity.Product;
import com.xyz.roemahduren.domain.model.request.ProductRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.model.response.ProductResponse;
import com.xyz.roemahduren.domain.model.response.SupplierProductResponse;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.domain.service.CategoryService;
import com.xyz.roemahduren.domain.service.ProductService;
import com.xyz.roemahduren.domain.service.SupplierProductService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.input.RoundedComboBox;
import com.xyz.roemahduren.presentation.component.input.RoundedTextFieldPanel;
import com.xyz.roemahduren.presentation.component.table.TableActionCellEditor;
import com.xyz.roemahduren.presentation.component.table.TableActionCellRender;
import com.xyz.roemahduren.presentation.event.TableActionEvent;
import com.xyz.roemahduren.presentation.screen.ProductScreen;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.*;

import static com.xyz.roemahduren.constant.ConstantMessage.PRODUCT;

public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BranchService branchService;
    private final ProductScreen productScreen;
    private final SupplierProductService supplierProductService;
    private final CustomDialog dialog;

    private DefaultTableModel model;
    private List<ProductResponse> products;
    private Product product;
    private List<Category> categories;
    private List<Branch> branches;
    private List<SupplierProductResponse> supplierProductResponses;

    public ProductController(ProductService productService, CategoryService categoryService, BranchService branchService, ProductScreen productScreen, SupplierProductService supplierProductService, CustomDialog dialog) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.branchService = branchService;
        this.productScreen = productScreen;
        this.supplierProductService = supplierProductService;
        this.dialog = dialog;
        initController();
        initTable();
        initForm();
    }

    private void initForm() {
        categories = categoryService.getAll();
        RoundedComboBox<String> categoryComboBox = productScreen.getCategoryComboBox().getComboBox();
        categoryComboBox.removeAllItems();
        categoryComboBox.addItem("--Pilih Kategori--");

        for (Category category : categories) {
            categoryComboBox.addItem(category.getName());
        }

        branches = branchService.getAll();
        RoundedComboBox<String> branchComboBox = productScreen.getBranchComboBox().getComboBox();
        branchComboBox.removeAllItems();
        branchComboBox.addItem("--Pilih Cabang--");

        List<Branch> branches = branchService.getAll();
        for (Branch branch : branches) {
            branchComboBox.addItem(branch.getName());
        }

        RoundedComboBox<String> productComboBox = productScreen.getProductNameComboBox().getComboBox();
        productComboBox.removeAllItems();
        productComboBox.addItem("--Pilih Produk--");

        supplierProductResponses = supplierProductService.getAll();
        for (SupplierProductResponse spr : supplierProductResponses) {
            productComboBox.addItem(spr.getProductName());
        }
    }

    private void initController() {
        productScreen.getSaveBtn().addActionListener(this::saveProduct);
        productScreen.getClearBtn().addActionListener(this::clearForm);
        productScreen.getSearchBtn().addActionListener(this::searchProduct);
    }

    private void searchProduct(ActionEvent actionEvent) {
        RoundedTextFieldPanel searchTextField = productScreen.getSearchTextField();
        if (searchTextField.getValue().isEmpty() || searchTextField.getValue().equals("")) {
            initTable();
            return;
        }

        products = productService.getAllByName(searchTextField.getValue());

        if (products.isEmpty()) {
            SwingUtil.setEmptyState(productScreen.getScrollTable());
        } else {
            productScreen.getScrollTable().setViewportView(productScreen.getProductTable());
        }

        model.setRowCount(0);
        int counter = 0;
        for (ProductResponse product : products) {
            model.addRow(new Object[]{++counter, product.getName(), product.getPrice(), product.getStock(), product.getBranch(), product.getValidString()});
        }
    }

    private void clearForm(ActionEvent actionEvent) {
        clearForm();
    }

    private void clearForm() {
        productScreen.getSaveBtn().setText("Simpan");
        productScreen.getProductNameComboBox().getComboBox().setSelectedIndex(0);
        productScreen.getPriceNumberFormattedField().setValue("0");
        productScreen.getCategoryComboBox().getComboBox().setSelectedIndex(0);
        productScreen.getStockNumberFormattedField().setValue("0");
        productScreen.getBranchComboBox().getComboBox().setSelectedIndex(0);
        productScreen.getIsActiveCheckbox().getCheckbox().setSelected(false);
        productScreen.getIsActiveCheckbox().setVisible(false);
        initForm();
        clearError();
        product = null;
    }

    private void saveProduct(ActionEvent actionEvent) {
        if (product == null) {
            createProduct();
            return;
        }

        if (dialog.getConfirmUpdateDialog() != 1) return;
        updateProduct();
    }

    private void updateProduct() {
        clearError();
        ProductScreen screen = productScreen;

        new ServiceWorker<>(() -> {
            setLoading();
            int selectedIndexCategory = screen.getCategoryComboBox().getComboBox().getSelectedIndex();
            int selectedIndexBranch = screen.getBranchComboBox().getComboBox().getSelectedIndex();
            int selectedIndexProduct = screen.getProductNameComboBox().getComboBox().getSelectedIndex();

            productAndCategoryAndBranchValidation(selectedIndexProduct, selectedIndexCategory, selectedIndexBranch);

            Category category = categories.get(selectedIndexCategory - 1);
            Branch branch = branches.get(selectedIndexBranch - 1);
            SupplierProductResponse supplierProductResponse = supplierProductResponses.get(selectedIndexProduct - 1);

            ProductRequest request = new ProductRequest(
                    product.getId(),
                    supplierProductResponse.getId(),
                    category.getId(),
                    Long.parseLong(screen.getPriceNumberFormattedField().getValue()),
                    Integer.parseInt(screen.getStockNumberFormattedField().getValue()),
                    branch.getId(),
                    screen.getIsActiveCheckbox().getValue()
            );
            ValidationUtil.validate(request);

            return productService.update(request);
        }, productResponse -> {
            clearForm();
            initTable();
            initForm();
            dialog.getSuccessUpdateMessageDialog(PRODUCT);
        }, throwable -> {
            if (throwable instanceof ValidationException) {
                setError((ValidationException) throwable);
                return;
            }
            dialog.getFailedMessageDialog(throwable.getMessage());
        }, this::clearLoading).execute();
    }

    private void createProduct() {
        clearError();
        ProductScreen screen = productScreen;

        new ServiceWorker<>(() -> {
            setLoading();

            int selectedIndexCategory = screen.getCategoryComboBox().getComboBox().getSelectedIndex();
            int selectedIndexBranch = screen.getBranchComboBox().getComboBox().getSelectedIndex();
            int selectedIndexProduct = screen.getProductNameComboBox().getComboBox().getSelectedIndex();
            productAndCategoryAndBranchValidation(selectedIndexProduct, selectedIndexCategory, selectedIndexBranch);

            Category category = categories.get(selectedIndexCategory - 1);
            Branch branch = branches.get(selectedIndexBranch - 1);
            SupplierProductResponse supplierProductResponse = supplierProductResponses.get(selectedIndexProduct - 1);

            ProductRequest request = new ProductRequest(
                    supplierProductResponse.getId(),
                    category.getId(),
                    Long.parseLong(screen.getPriceNumberFormattedField().getValue()),
                    Integer.parseInt(screen.getStockNumberFormattedField().getValue()),
                    branch.getId(),
                    true
            );
            ValidationUtil.validate(request);

            return productService.create(request);
        }, productResponse -> {
            clearForm();
            initTable();
            initForm();
            dialog.getSuccessCreatedMessageDialog(PRODUCT);
        }, throwable -> {
            if (throwable instanceof ValidationException) {
                setError((ValidationException) throwable);
                return;
            }
            dialog.getFailedMessageDialog(throwable.getMessage());
            throw new RuntimeException(throwable);
        }, this::clearLoading).execute();
    }

    private void productAndCategoryAndBranchValidation(int selectedIndexProduct, int selectedIndexCategory, int selectedIndexBranch) {
        HashSet<ErrorValidationModel> errors = new HashSet<>();

        if (selectedIndexProduct > 0) {
            SupplierProductResponse supplierProductResponse = supplierProductResponses.get(selectedIndexProduct - 1);
            int stockReq = Integer.parseInt(productScreen.getStockNumberFormattedField().getValue());
            int result = supplierProductResponse.getStock() - stockReq;

            if (Integer.parseInt(productScreen.getPriceNumberFormattedField().getValue()) < supplierProductResponse.getPrice()) {
                HashSet<String> errorMessages = new HashSet<>();
                String format = String.format("Harga jual tidak boleh kurang dari harga beli (Harga beli: %d)", supplierProductResponse.getPrice());
                errorMessages.add(format);
                errors.add(new ErrorValidationModel("price", errorMessages));
                throw new ValidationException(errors);
            }

            if (!Objects.isNull(product) && (supplierProductResponse.getStock() + product.getStock()) >= stockReq) {
                return;
            }

            if (!Objects.isNull(product) && stockReq <= product.getStock()) {
                return;
            }

            if (result < 0) {
                HashSet<String> errorMessages = new HashSet<>();
                String format = String.format("Sisa Stok dari %s: %s", supplierProductResponse.getProductName(), supplierProductResponse.getStock());
                errorMessages.add(format);
                errors.add(new ErrorValidationModel("stock", errorMessages));
            }

        }

        if (selectedIndexProduct == 0) {
            HashSet<String> errorMessages = new HashSet<>();
            errorMessages.add("Produk wajib dipilih");
            errors.add(new ErrorValidationModel("supplierProductId", errorMessages));
        }

        if (selectedIndexCategory == 0) {
            HashSet<String> errorMessages = new HashSet<>();
            errorMessages.add("Kategori wajib dipilih");
            errors.add(new ErrorValidationModel("categoryId", errorMessages));
        }

        if (selectedIndexBranch == 0) {
            HashSet<String> errorMessages = new HashSet<>();
            errorMessages.add("Cabang wajib dipilih");
            errors.add(new ErrorValidationModel("branchId", errorMessages));
        }

        if (!errors.isEmpty()) throw new ValidationException(errors);
    }

    private void clearError() {
        productScreen.getProductNameComboBox().clearErrorMessage();
        productScreen.getCategoryComboBox().clearErrorMessage();
        productScreen.getPriceNumberFormattedField().clearErrorMessage();
        productScreen.getStockNumberFormattedField().clearErrorMessage();
        productScreen.getBranchComboBox().clearErrorMessage();
    }

    private void setError(ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            Set<String> messages = validationModel.getMessages();
            String message = ValidationUtil.getMessage(messages);

            if (validationModel.getPath().equalsIgnoreCase("name")) {
                productScreen.getProductNameComboBox().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("categoryId")) {
                productScreen.getCategoryComboBox().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("price")) {
                productScreen.getPriceNumberFormattedField().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("stock")) {
                productScreen.getStockNumberFormattedField().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("branchId")) {
                productScreen.getBranchComboBox().setErrorMessage(message);
            } else if (validationModel.getPath().equalsIgnoreCase("supplierProductId")) {
                productScreen.getProductNameComboBox().setErrorMessage(message);
            }
        }
    }

    public ProductScreen getProductScreen() {
        initForm();
        initTable();
        return productScreen;
    }

    public void initTable() {
        final String[] HEADERS = {"#", "Nama Produk", "Kategori", "Harga", "Stok", "Cabang", "Status", "Aksi"};
        model = new DefaultTableModel(null, HEADERS);

        products = productService.getAll();

        if (products.isEmpty()) {
            SwingUtil.setEmptyState(productScreen.getScrollTable());
        } else {
            productScreen.getScrollTable().setViewportView(productScreen.getProductTable());
        }

        int counter = 0;
        for (ProductResponse product : products) {
            model.addRow(new Object[]{
                    ++counter,
                    product.getName(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getStock(),
                    product.getBranch(),
                    product.getValidString()
            });
        }

        productScreen.getProductTable().setModel(model);

        TableActionEvent tableActionEvent = getTableActionEvent();
        productScreen.getProductTable().getColumnModel().getColumn(HEADERS.length - 1).setCellRenderer(new TableActionCellRender());
        productScreen.getProductTable().getColumnModel().getColumn(HEADERS.length - 1).setCellEditor(new TableActionCellEditor(tableActionEvent));
    }

    private void deleteProduct(int row) {
        int confirmDeleteDialog = dialog.getConfirmDeleteDialog();
        if (confirmDeleteDialog != 1) return;
        new ServiceWorker<>(
                () -> {
                    ProductResponse productResponse = products.get(row);
                    productService.deleteById(productResponse.getId());
                    return true;
                },
                o -> {
                    dialog.getSuccessUpdateMessageDialog(PRODUCT);
                    initTable();
                    initForm();
                },
                throwable -> {
                    dialog.getFailedMessageDialog(throwable.getMessage());
                    throw new RuntimeException(throwable);
                },
                () -> {
                }
        ).execute();
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

    private void setLoading() {
        SwingUtil.setLoading(productScreen.getSaveBtn());
    }

    private void clearLoading() {
        SwingUtil.clearSecondaryLoading(productScreen.getSaveBtn(), product != null ? ConstantMessage.BTN_TEXT_SAVE : ConstantMessage.BTN_TEXT_UPDATE);
    }

    private void setForm(int row) {
        ProductResponse productResponse = products.get(row);
        productScreen.getIsActiveCheckbox().setVisible(true);
        productScreen.getSaveBtn().setText("Ubah");
        productScreen.getProductNameComboBox().getComboBox().setSelectedItem(productResponse.getName());
        productScreen.getPriceNumberFormattedField().setValue(String.valueOf(productResponse.getPrice().intValue()));
        productScreen.getCategoryComboBox().getComboBox().setSelectedItem(productResponse.getCategory());
        productScreen.getStockNumberFormattedField().setValue(productResponse.getStock().toString());
        productScreen.getBranchComboBox().getComboBox().setSelectedItem(productResponse.getBranch());
        productScreen.getIsActiveCheckbox().getCheckbox().setSelected(productResponse.getValid());

        product = new Product(productResponse.getId(), productResponse.getName(), productResponse.getPrice(), productResponse.getStock(), null, null, productResponse.getValid());
        Optional<Category> category = categoryService.getAllByName(productResponse.getCategory()).stream().findFirst();
        category.ifPresent(value -> product.setCategoryId(value.getId()));
        Optional<Branch> branch = branchService.getByName(productResponse.getName()).stream().findFirst();
        branch.ifPresent(value -> product.setBranchId(value.getId()));
    }
}
