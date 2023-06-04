package com.xyz.roemahduren.presentation.screen;

import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.component.input.CheckboxPanel;
import com.xyz.roemahduren.presentation.component.input.RoundedComboBoxPanel;
import com.xyz.roemahduren.presentation.component.input.RoundedNumberFormattedFieldPanel;
import com.xyz.roemahduren.presentation.component.input.RoundedTextAreaPanel;
import com.xyz.roemahduren.presentation.component.input.RoundedTextFieldPanel;
import com.xyz.roemahduren.presentation.component.panel.RoundedPanel;
import com.xyz.roemahduren.presentation.component.table.Table;
import com.xyz.roemahduren.util.SwingUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class SupplierScreen extends javax.swing.JPanel {

    /**
     * Creates new form BranchScreen
     */
    public SupplierScreen() {
        initComponents();
        SwingUtil.fixScroll(supplierScrollPane);
        SwingUtil.fixScroll(productSupplierScrollPane);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        jPanel1 = new JPanel();
        supplierFormPanel = new RoundedPanel();
        nameTextField = new RoundedTextFieldPanel();
        addressTextArea = new RoundedTextAreaPanel();
        saveBtnSupplier = new RoundedButton();
        clearBtnSupplier = new RoundedButton();
        titleScreen = new JLabel();
        formPanel = new RoundedPanel();
        productNameTextField = new RoundedTextFieldPanel();
        saveBtnProductSupplier = new RoundedButton();
        clearBtnProductSupplier = new RoundedButton();
        priceNumberFormattedField = new RoundedNumberFormattedFieldPanel();
        supplierComboBox = new RoundedComboBoxPanel();
        productLabelForm = new JLabel();
        stockNumberFormattedField = new RoundedNumberFormattedFieldPanel();
        productSupplierScrollPane = new JScrollPane();
        productSupplierTable = new Table();
        titleTableProduct = new JLabel();
        supplierScrollPane = new JScrollPane();
        supplierTable = new Table();
        titleTableProduct1 = new JLabel();

        jLabel1.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Hello Admin!");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new Color(255, 255, 254));
        setMinimumSize(new Dimension(800, 1600));
        setPreferredSize(new Dimension(800, 1600));
        setSize(new Dimension(800, 1600));

        supplierFormPanel.setBackground(new Color(245, 245, 245));
        supplierFormPanel.setCornerRadius(8);

        nameTextField.setBackground(new Color(245, 245, 245));
        nameTextField.setLabelErrorText("");
        nameTextField.setLabelText("Nama Supplier");

        addressTextArea.setBackground(new Color(245, 245, 245));
        addressTextArea.setLabelErrorText("");
        addressTextArea.setLabelText("Alamat Supplier");

        saveBtnSupplier.setText("Simpan");
        saveBtnSupplier.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        clearBtnSupplier.setBackground(new Color(221, 83, 83));
        clearBtnSupplier.setText("Cear");
        clearBtnSupplier.setBorderColor(new Color(221, 83, 83));
        clearBtnSupplier.setColor(new Color(221, 83, 83));
        clearBtnSupplier.setColorClick(new Color(204, 77, 77));
        clearBtnSupplier.setColorOver(new Color(204, 77, 77));
        clearBtnSupplier.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        GroupLayout supplierFormPanelLayout = new GroupLayout(supplierFormPanel);
        supplierFormPanel.setLayout(supplierFormPanelLayout);
        supplierFormPanelLayout.setHorizontalGroup(supplierFormPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(supplierFormPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(supplierFormPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(supplierFormPanelLayout.createSequentialGroup()
                        .addComponent(saveBtnSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clearBtnSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(supplierFormPanelLayout.createSequentialGroup()
                        .addGroup(supplierFormPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(addressTextArea, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameTextField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24))))
        );
        supplierFormPanelLayout.setVerticalGroup(supplierFormPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(supplierFormPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addressTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(supplierFormPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtnSupplier, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtnSupplier, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        titleScreen.setFont(new Font("Helvetica Neue", 1, 24)); // NOI18N
        titleScreen.setForeground(new Color(2, 8, 38));
        titleScreen.setText("Manajemen Pemasok");

        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setCornerRadius(8);

        productNameTextField.setBackground(new Color(245, 245, 245));
        productNameTextField.setLabelErrorText("");
        productNameTextField.setLabelText("Nama Produk");

        saveBtnProductSupplier.setText("Simpan");
        saveBtnProductSupplier.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        clearBtnProductSupplier.setBackground(new Color(221, 83, 83));
        clearBtnProductSupplier.setText("Clear");
        clearBtnProductSupplier.setBorderColor(new Color(221, 83, 83));
        clearBtnProductSupplier.setColor(new Color(221, 83, 83));
        clearBtnProductSupplier.setColorClick(new Color(204, 77, 77));
        clearBtnProductSupplier.setColorOver(new Color(204, 77, 77));
        clearBtnProductSupplier.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        priceNumberFormattedField.setBackground(new Color(245, 245, 245));
        priceNumberFormattedField.setLabelErrorText("");
        priceNumberFormattedField.setLabelText("Harga Beli");
        priceNumberFormattedField.setValue("0");

        supplierComboBox.setBackground(new Color(245, 245, 245));
        supplierComboBox.setLabelErrorText("");
        supplierComboBox.setLabelText("Supplier");

        productLabelForm.setFont(new Font("Helvetica Neue", 0, 22)); // NOI18N
        productLabelForm.setForeground(new Color(2, 8, 38));
        productLabelForm.setText("Form Produk");

        stockNumberFormattedField.setBackground(new Color(245, 245, 245));
        stockNumberFormattedField.setLabelErrorText("");
        stockNumberFormattedField.setLabelText("Stok");
        stockNumberFormattedField.setValue("0");

        GroupLayout formPanelLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(supplierComboBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productNameTextField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(stockNumberFormattedField, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(productLabelForm)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(saveBtnProductSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clearBtnProductSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(priceNumberFormattedField, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        formPanelLayout.setVerticalGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(productLabelForm)
                .addGap(28, 28, 28)
                .addComponent(supplierComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(productNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(priceNumberFormattedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockNumberFormattedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtnProductSupplier, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtnProductSupplier, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        productSupplierTable.setModel(new DefaultTableModel(
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
        productSupplierScrollPane.setViewportView(productSupplierTable);

        titleTableProduct.setFont(new Font("Helvetica Neue", 0, 22)); // NOI18N
        titleTableProduct.setForeground(new Color(2, 8, 38));
        titleTableProduct.setText("Daftar Produk Pemasok");

        supplierTable.setModel(new DefaultTableModel(
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
        supplierScrollPane.setViewportView(supplierTable);

        titleTableProduct1.setFont(new Font("Helvetica Neue", 0, 22)); // NOI18N
        titleTableProduct1.setForeground(new Color(2, 8, 38));
        titleTableProduct1.setText("Daftar Pemasok");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(formPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supplierFormPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supplierScrollPane)
                            .addComponent(productSupplierScrollPane, GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(titleTableProduct)
                                .addGap(459, 459, 459)))
                        .addGap(75, 75, 75))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(titleScreen)
                            .addComponent(titleTableProduct1))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(titleScreen)
                .addGap(32, 32, 32)
                .addComponent(supplierFormPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titleTableProduct1)
                .addGap(18, 18, 18)
                .addComponent(supplierScrollPane, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(formPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titleTableProduct)
                .addGap(18, 18, 18)
                .addComponent(productSupplierScrollPane, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public RoundedTextAreaPanel getAddressTextArea() {
        return addressTextArea;
    }

    public RoundedButton getClearBtnProductSupplier() {
        return clearBtnProductSupplier;
    }

    public RoundedButton getClearBtnSupplier() {
        return clearBtnSupplier;
    }

    public RoundedPanel getFormPanel() {
        return formPanel;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public RoundedTextFieldPanel getNameTextField() {
        return nameTextField;
    }

    public RoundedNumberFormattedFieldPanel getPriceNumberFormattedField() {
        return priceNumberFormattedField;
    }

    public JLabel getProductLabelForm() {
        return productLabelForm;
    }

    public RoundedTextFieldPanel getProductNameTextField() {
        return productNameTextField;
    }

    public JScrollPane getProductSupplierScrollPane() {
        return productSupplierScrollPane;
    }

    public Table getProductSupplierTable() {
        return productSupplierTable;
    }

    public RoundedButton getSaveBtnProductSupplier() {
        return saveBtnProductSupplier;
    }

    public RoundedButton getSaveBtnSupplier() {
        return saveBtnSupplier;
    }

    public RoundedNumberFormattedFieldPanel getStockNumberFormattedField() {
        return stockNumberFormattedField;
    }

    public RoundedComboBoxPanel getSupplierComboBox() {
        return supplierComboBox;
    }

    public RoundedPanel getSupplierFormPanel() {
        return supplierFormPanel;
    }

    public JScrollPane getSupplierScrollPane() {
        return supplierScrollPane;
    }

    public Table getSupplierTable() {
        return supplierTable;
    }

    public JLabel getTitleScreen() {
        return titleScreen;
    }

    public JLabel getTitleTableProduct() {
        return titleTableProduct;
    }

    public JLabel getTitleTableProduct1() {
        return titleTableProduct1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoundedTextAreaPanel addressTextArea;
    private RoundedButton clearBtnProductSupplier;
    private RoundedButton clearBtnSupplier;
    private RoundedPanel formPanel;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private RoundedTextFieldPanel nameTextField;
    private RoundedNumberFormattedFieldPanel priceNumberFormattedField;
    private JLabel productLabelForm;
    private RoundedTextFieldPanel productNameTextField;
    private JScrollPane productSupplierScrollPane;
    private Table productSupplierTable;
    private RoundedButton saveBtnProductSupplier;
    private RoundedButton saveBtnSupplier;
    private RoundedNumberFormattedFieldPanel stockNumberFormattedField;
    private RoundedComboBoxPanel supplierComboBox;
    private RoundedPanel supplierFormPanel;
    private JScrollPane supplierScrollPane;
    private Table supplierTable;
    private JLabel titleScreen;
    private JLabel titleTableProduct;
    private JLabel titleTableProduct1;
    // End of variables declaration//GEN-END:variables
}
