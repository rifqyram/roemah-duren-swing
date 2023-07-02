package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.entity.Category;
import com.xyz.roemahduren.domain.model.request.CategoryRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.CategoryService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.component.table.TableActionCellEditor;
import com.xyz.roemahduren.presentation.component.table.TableActionCellRender;
import com.xyz.roemahduren.presentation.event.TableActionEvent;
import com.xyz.roemahduren.presentation.screen.CategoryScreen;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import static com.xyz.roemahduren.constant.ConstantMessage.*;

public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryScreen categoryScreen;
    private final CustomDialog dialog;

    private List<Category> categories;
    private Category category;
    private DefaultTableModel model;


    public CategoryController(CategoryService categoryService, CategoryScreen categoryScreen, CustomDialog dialog) {
        this.categoryService = categoryService;
        this.categoryScreen = categoryScreen;
        this.dialog = dialog;

        initTable();
        initController();
    }

    private void initController() {
        categoryScreen.getSaveBtn().addActionListener(this::saveCategory);
        categoryScreen.getClearBtn().addActionListener(this::clearForm);
        categoryScreen.getSearchBtn().addActionListener(this::searchCategory);
        categoryScreen.getSearchTextField().getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (categoryScreen.getSearchTextField().getValue().length() == 0) {
                    initTable();
                }
            }
        });
    }

    private void searchCategory(ActionEvent actionEvent) {
        String value = categoryScreen.getSearchTextField().getValue();
        if (value.length() == 0) {
            initTable();
            return;
        }

        List<Category> categoryList = categoryService.getAllByName(value);

        if (categoryList.isEmpty()) {
            SwingUtil.setEmptyState(categoryScreen.getScrollTable());
            return;
        }

        model.setRowCount(0);
        int counter = 0;
        for (Category category : categoryList) {
            model.addRow(new Object[]{++counter, category.getName()});
        }
    }

    private void clearForm(ActionEvent actionEvent) {
        clearForm();
    }

    private void saveCategory(ActionEvent actionEvent) {
        categoryScreen.getNameTextField().clearErrorMessage();

        if (category == null) {
            createCategory();
            return;
        }

        int confirmUpdateDialog = dialog.getConfirmUpdateDialog();
        if (confirmUpdateDialog != 1) return;
        updateCategory();
    }

    private void updateCategory() {
        RoundedButton saveBtn = categoryScreen.getSaveBtn();
        new ServiceWorker<>(
                () -> {
                    SwingUtil.setLoading(saveBtn);
                    CategoryRequest categoryRequest = new CategoryRequest(category.getId(), categoryScreen.getNameTextField().getValue());
                    ValidationUtil.validate(categoryRequest);
                    return categoryService.update(categoryRequest);
                },
                category -> {
                    dialog.getSuccessUpdateMessageDialog(CATEGORY);
                    initTable();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        getErrorMessages((ValidationException) throwable);
                        return;
                    }
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> {
                    SwingUtil.clearSecondaryLoading(saveBtn, saveBtn.getText());
                    clearForm();
                    category = null;
                }
        ).execute();
    }

    private void createCategory() {
        RoundedButton saveBtn = categoryScreen.getSaveBtn();
        new ServiceWorker<>(
                () -> {
                    SwingUtil.setLoading(saveBtn);
                    CategoryRequest categoryRequest = new CategoryRequest(categoryScreen.getNameTextField().getValue());
                    ValidationUtil.validate(categoryRequest);
                    return categoryService.create(categoryRequest);
                },
                category -> {
                    dialog.getSuccessCreatedMessageDialog(CATEGORY);
                    initTable();
                },
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        getErrorMessages((ValidationException) throwable);
                        return;
                    }

                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> {
                    SwingUtil.clearSecondaryLoading(saveBtn, saveBtn.getText());
                    clearForm();
                    category = null;
                }).execute();
    }

    private void getErrorMessages(ValidationException throwable) {
        Set<ErrorValidationModel> validationModels = throwable.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            Set<String> messages = validationModel.getMessages();
            String htmlMessage = ValidationUtil.getHtmlMessage(messages, categoryScreen.getNameTextField().getPreferredSize().width);
            categoryScreen.getNameTextField().setErrorMessage(htmlMessage);
        }
    }

    public void initTable() {
        final String[] HEADERS = {"#", "Nama Kategori", "Aksi"};
        model = new DefaultTableModel(null, HEADERS);

        categories = categoryService.getAll();

        if (categories.isEmpty()) {
            SwingUtil.setEmptyState(categoryScreen.getScrollTable());
        } else {
            categoryScreen.getScrollTable().setViewportView(categoryScreen.getCategoryTable());
        }


        int counter = 0;
        for (Category category : categories) {
            model.addRow(new Object[]{++counter, category.getName()});
        }

        categoryScreen.getCategoryTable().setModel(model);
        TableActionEvent tableActionEvent = getTableActionEvent();
        categoryScreen.getCategoryTable().getColumnModel().getColumn(HEADERS.length - 1).setCellRenderer(new TableActionCellRender());
        categoryScreen.getCategoryTable().getColumnModel().getColumn(HEADERS.length - 1).setCellEditor(new TableActionCellEditor(tableActionEvent));
    }

    private TableActionEvent getTableActionEvent() {
        return new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                setForm(row);
            }

            @Override
            public void onDelete(int row) {
                deleteCategory(row);
            }
        };
    }

    private void deleteCategory(int row) {
        int confirmDeleteDialog = dialog.getConfirmDeleteDialog();
        if (confirmDeleteDialog != 1) return;

        new ServiceWorker<>(
                () -> {
                    Category category = categories.get(row);
                    categoryService.deleteById(category.getId());
                    return true;
                },
                category -> {
                    dialog.getSuccessDeletedMessageDialog(CATEGORY);
                    initTable();
                },
                throwable -> dialog.getFailedMessageDialog(throwable.getMessage()),
                () -> {}
        ).execute();
    }

    private void setForm(int row) {
        category = categories.get(row);
        categoryScreen.getNameTextField().setValue(category.getName());
        categoryScreen.getSaveBtn().setText(BTN_TEXT_UPDATE);
    }

    private void clearForm() {
        categoryScreen.getNameTextField().setValue("");
        categoryScreen.getSaveBtn().setText(BTN_TEXT_SAVE);
        category = null;
    }

    public CategoryScreen getCategoryScreen() {
        return categoryScreen;
    }
}
