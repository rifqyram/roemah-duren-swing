/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.xyz.roemahduren.presentation.screen;

import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.component.input.CheckboxPanel;
import com.xyz.roemahduren.presentation.component.input.RoundedComboBoxPanel;
import com.xyz.roemahduren.presentation.component.input.RoundedNumberFormattedFieldPanel;
import com.xyz.roemahduren.presentation.component.input.RoundedTextFieldPanel;
import com.xyz.roemahduren.presentation.component.panel.RoundedPanel;
import com.xyz.roemahduren.presentation.component.table.Table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class ProductScreen extends javax.swing.JPanel {

    /**
     * Creates new form ProductScreen
     */
    public ProductScreen() {
        initComponents();
        productTable.fixTable(scrollTable);
        isActiveCheckbox.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        formPanel = new RoundedPanel();
        saveBtn = new RoundedButton();
        clearBtn = new RoundedButton();
        priceNumberFormattedField = new RoundedNumberFormattedFieldPanel();
        categoryComboBox = new RoundedComboBoxPanel();
        stockNumberFormattedField = new RoundedNumberFormattedFieldPanel();
        branchTextField = new RoundedComboBoxPanel();
        isActiveCheckbox = new CheckboxPanel();
        nameTextField = new RoundedComboBoxPanel();
        titleScreen = new JLabel();
        titleTable = new JLabel();
        searchPanel = new JPanel();
        searchTextField = new RoundedTextFieldPanel();
        searchBtn = new RoundedButton();
        scrollTable = new JScrollPane();
        productTable = new Table();

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBackground(new Color(255, 255, 254));
        setMinimumSize(new Dimension(800, 800));
        setPreferredSize(new Dimension(800, 800));

        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setCornerRadius(8);

        saveBtn.setText("Simpan");
        saveBtn.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        clearBtn.setBackground(new Color(221, 83, 83));
        clearBtn.setText("Clear");
        clearBtn.setBorderColor(new Color(221, 83, 83));
        clearBtn.setColor(new Color(221, 83, 83));
        clearBtn.setColorClick(new Color(204, 77, 77));
        clearBtn.setColorOver(new Color(204, 77, 77));
        clearBtn.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        priceNumberFormattedField.setBackground(new Color(245, 245, 245));
        priceNumberFormattedField.setLabelErrorText("");
        priceNumberFormattedField.setLabelText("Harga");
        priceNumberFormattedField.setValue("0");

        categoryComboBox.setBackground(new Color(245, 245, 245));
        categoryComboBox.setLabelErrorText("");
        categoryComboBox.setLabelText("Kategori");

        stockNumberFormattedField.setBackground(new Color(245, 245, 245));
        stockNumberFormattedField.setLabelErrorText("");
        stockNumberFormattedField.setLabelText("Stok");
        stockNumberFormattedField.setValue("0");

        branchTextField.setBackground(new Color(245, 245, 245));
        branchTextField.setLabelErrorText("");
        branchTextField.setLabelText("Cabang");

        isActiveCheckbox.setBackground(new Color(245, 245, 245));
        isActiveCheckbox.setErrorLabel("");
        isActiveCheckbox.setLabel("");
        isActiveCheckbox.setText("Aktif");

        nameTextField.setBackground(new Color(245, 245, 245));
        nameTextField.setLabelErrorText("");
        nameTextField.setLabelText("Nama Produk");

        GroupLayout formPanelLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(isActiveCheckbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(nameTextField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.LEADING, formPanelLayout.createSequentialGroup()
                                .addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 483, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.LEADING, formPanelLayout.createSequentialGroup()
                                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(priceNumberFormattedField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stockNumberFormattedField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(categoryComboBox, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                                    .addComponent(branchTextField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(24, 24, 24))))
        );
        formPanelLayout.setVerticalGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(priceNumberFormattedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(stockNumberFormattedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(branchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isActiveCheckbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        titleScreen.setFont(new Font("Helvetica Neue", 1, 24)); // NOI18N
        titleScreen.setForeground(new Color(2, 8, 38));
        titleScreen.setText("Manajemen Produk");

        titleTable.setFont(new Font("Helvetica Neue", 0, 22)); // NOI18N
        titleTable.setForeground(new Color(2, 8, 38));
        titleTable.setText("Daftar Produk");

        searchPanel.setBackground(new Color(255, 255, 254));

        searchTextField.setBackground(new Color(255, 255, 254));
        searchTextField.setLabelErrorText("");
        searchTextField.setLabelText("Cari Nama");

        searchBtn.setText("Cari");
        searchBtn.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        GroupLayout searchPanelLayout = new GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(searchPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(searchTextField, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(searchPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        productTable.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTable.setViewportView(productTable);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleScreen)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollTable, GroupLayout.Alignment.LEADING)
                            .addComponent(formPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleTable)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                                .addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(75, 75, 75))))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(titleScreen)
                .addGap(23, 23, 23)
                .addComponent(formPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(titleTable))
                    .addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollTable, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    public RoundedComboBoxPanel getBranchComboBox() {
        return branchTextField;
    }

    public RoundedComboBoxPanel getCategoryComboBox() {
        return categoryComboBox;
    }

    public JTable getProductTable() {
        return productTable;
    }

    public RoundedButton getClearBtn() {
        return clearBtn;
    }

    public RoundedPanel getFormPanel() {
        return formPanel;
    }

    public CheckboxPanel getIsActiveCheckbox() {
        return isActiveCheckbox;
    }

    public RoundedComboBoxPanel getProductNameComboBox() {
        return nameTextField;
    }

    public RoundedNumberFormattedFieldPanel getPriceNumberFormattedField() {
        return priceNumberFormattedField;
    }

    public RoundedButton getSaveBtn() {
        return saveBtn;
    }

    public JScrollPane getScrollTable() {
        return scrollTable;
    }

    public RoundedButton getSearchBtn() {
        return searchBtn;
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public RoundedTextFieldPanel getSearchTextField() {
        return searchTextField;
    }

    public RoundedNumberFormattedFieldPanel getStockNumberFormattedField() {
        return stockNumberFormattedField;
    }

    public JLabel getTitleScreen() {
        return titleScreen;
    }

    public JLabel getTitleTable() {
        return titleTable;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoundedComboBoxPanel branchTextField;
    private RoundedComboBoxPanel categoryComboBox;
    private RoundedButton clearBtn;
    private RoundedPanel formPanel;
    private CheckboxPanel isActiveCheckbox;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private RoundedComboBoxPanel nameTextField;
    private RoundedNumberFormattedFieldPanel priceNumberFormattedField;
    private Table productTable;
    private RoundedButton saveBtn;
    private JScrollPane scrollTable;
    private RoundedButton searchBtn;
    private JPanel searchPanel;
    private RoundedTextFieldPanel searchTextField;
    private RoundedNumberFormattedFieldPanel stockNumberFormattedField;
    private JLabel titleScreen;
    private JLabel titleTable;
    // End of variables declaration//GEN-END:variables
}
