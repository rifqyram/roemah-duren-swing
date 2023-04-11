package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.domain.entity.Category;
import com.xyz.roemahduren.domain.model.request.CategoryRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.service.CategoryService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.DataEmpty;
import com.xyz.roemahduren.presentation.component.table.Table;
import com.xyz.roemahduren.presentation.component.table.TableActionCellEditor;
import com.xyz.roemahduren.presentation.component.table.TableActionCellRender;
import com.xyz.roemahduren.presentation.event.TableActionEvent;
import com.xyz.roemahduren.presentation.screen.CategoryScreen;
import com.xyz.roemahduren.presentation.theme.MaterialColor;
import com.xyz.roemahduren.util.DatabaseWorker;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryScreen categoryScreen;

    private List<Category> categories;
    private DefaultTableModel categoryModel;
    private int row;

    public CategoryController(CategoryService categoryService, CategoryScreen categoryScreen) {
        this.categoryService = categoryService;
        this.categoryScreen = categoryScreen;
        categories = loadCategory();
        initTable();
        initController();
    }

    public void initTable() {
        String[] headers = {"#", "Category Name", "Action"};
        categoryModel = new DefaultTableModel(null, headers);
        categoryScreen.getCategoryTable().setModel(categoryModel);

        if (categories.isEmpty()) {
            setEmptyState();
        }

        refreshTable();
        TableActionEvent tableActionEvent = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                updateCategory(row);
            }

            @Override
            public void onDelete(int row) {
                deleteCategory(row);
            }
        };

        int lastColumn = categoryScreen.getCategoryTable().getColumnCount() - 1;
        categoryScreen.getCategoryTable().getColumnModel().getColumn(lastColumn).setCellRenderer(new TableActionCellRender());
        categoryScreen.getCategoryTable().getColumnModel().getColumn(lastColumn).setCellEditor(new TableActionCellEditor(tableActionEvent));
    }

    public void initController() {
        categoryScreen.getBtnSave().addActionListener(this::createCategory);
    }

    public void createCategory(ActionEvent actionEvent) {
        clearMessage();
        String text = categoryScreen.getBtnSave().getText();
        new DatabaseWorker<>(
                () -> {
                    setLoading();
                    CategoryRequest categoryRequest = new CategoryRequest(categoryScreen.getTfCategoryName().getText());
                    ValidationUtil.validate(categoryRequest);
                    Category category = categoryService.create(categoryRequest);

                    if (categories.isEmpty()) {
                        setTableState();
                    }

                    categories.add(category);
                    refreshTable();
                    clearForm();

                    return category;
                },
                category -> JOptionPane.showMessageDialog(null, "Success"),
                throwable -> {
                    if (throwable instanceof ValidationException) {
                        setErrorMessage((ValidationException) throwable);
                    }
                },
                () -> clearLoading(text)
        ).execute();
    }

    private void clearForm() {
        categoryScreen.getTfCategoryName().setText("");
    }

    private void setTableState() {
        Table categoryTable = categoryScreen.getCategoryTable();
        categoryScreen.getCategoryScrollPanel().setViewportView(categoryTable);
    }

    private void setLoading() {
        categoryScreen.getBtnSave().setText("Loading...");
        categoryScreen.getBtnSave().setEnabled(false);
        categoryScreen.getBtnSave().setBackground(MaterialColor.disbaleColor);
        categoryScreen.getBtnSave().setBorderColor(MaterialColor.disbaleColor);
    }

    private void clearLoading(String text) {
        categoryScreen.getBtnSave().setText(text);
        categoryScreen.getBtnSave().setEnabled(true);
        categoryScreen.getBtnSave().setBackground(MaterialColor.primaryColor);
        categoryScreen.getBtnSave().setBorderColor(MaterialColor.primaryColor);
    }

    public void updateCategory(int row) {

    }

    public void deleteCategory(int row) {

    }

    public List<Category> loadCategory() {
        List<Category> categoryList = categoryService.getAll();
        if (!categoryList.isEmpty()) {
            return categoryList;
        }
        return new ArrayList<>();
    }

    private void setEmptyState() {
        DataEmpty dataEmpty = new DataEmpty();
        categoryScreen.getCategoryScrollPanel().setViewportView(dataEmpty);
    }

    public void refreshTable() {
        categoryModel.setRowCount(0);

        int index = 0;
        for (Category category : categories) {
            categoryModel.addRow(new Object[]{++index, category.getName()});
        }
    }

    public void setErrorMessage(ValidationException exception) {
        Set<ErrorValidationModel> validationModels = exception.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            String htmlMessage = ValidationUtil.getHtmlMessage(validationModel.getMessages(), 200);
            categoryScreen.getErrorCategoryNameLabel().setText(htmlMessage);
        }
    }

    public void clearMessage() {
        categoryScreen.getErrorCategoryNameLabel().setText("");
    }

    public CategoryScreen getCategoryScreen() {
        return categoryScreen;
    }
}
