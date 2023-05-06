/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.xyz.roemahduren.presentation.screen;

import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.component.input.RoundedTextAreaPanel;
import com.xyz.roemahduren.presentation.component.input.RoundedTextFieldPanel;
import com.xyz.roemahduren.presentation.component.panel.RoundedPanel;
import com.xyz.roemahduren.presentation.component.table.Table;

import javax.swing.*;

/**
 *
 * @author user
 */
public class BranchScreen extends javax.swing.JPanel {

    /**
     * Creates new form BranchScreen
     */
    public BranchScreen() {
        initComponents();
        branchTable.fixTable(scrollTable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        formPanel = new com.xyz.roemahduren.presentation.component.panel.RoundedPanel();
        nameTextField = new com.xyz.roemahduren.presentation.component.input.RoundedTextFieldPanel();
        saveBtn = new com.xyz.roemahduren.presentation.component.RoundedButton();
        addressTextArea = new com.xyz.roemahduren.presentation.component.input.RoundedTextAreaPanel();
        titleScreen = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        branchTable = new com.xyz.roemahduren.presentation.component.table.Table();
        titleTable = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Hello Admin!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 254));
        setMinimumSize(new java.awt.Dimension(800, 800));
        setPreferredSize(new java.awt.Dimension(800, 800));
        setSize(new java.awt.Dimension(800, 800));

        formPanel.setBackground(new java.awt.Color(245, 245, 245));
        formPanel.setCornerRadius(8);

        nameTextField.setBackground(new java.awt.Color(245, 245, 245));
        nameTextField.setLabelErrorText("");
        nameTextField.setLabelText("Branch Name");

        saveBtn.setForeground(new java.awt.Color(255, 255, 254));
        saveBtn.setText("Save");
        saveBtn.setBorderColor(new java.awt.Color(140, 120, 81));
        saveBtn.setColor(new java.awt.Color(140, 120, 81));
        saveBtn.setColorClick(new java.awt.Color(107, 92, 64));
        saveBtn.setColorOver(new java.awt.Color(124, 106, 71));
        saveBtn.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N

        addressTextArea.setBackground(new java.awt.Color(245, 245, 245));
        addressTextArea.setLabelErrorText("");
        addressTextArea.setLabelText("Address");

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addressTextArea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24))))
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addressTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        titleScreen.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        titleScreen.setForeground(new java.awt.Color(2, 8, 38));
        titleScreen.setText("Branch Management");

        branchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "#", "Branch Name", "Address", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTable.setViewportView(branchTable);

        titleTable.setFont(new java.awt.Font("Helvetica Neue", 0, 22)); // NOI18N
        titleTable.setForeground(new java.awt.Color(2, 8, 38));
        titleTable.setText("Branch List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                            .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(75, 75, 75))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleTable)
                            .addComponent(titleScreen))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(titleScreen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titleTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
    }// </editor-fold>//GEN-END:initComponents

    public Table getBranchTable() {
        return branchTable;
    }

    public RoundedPanel getFormPanel() {
        return formPanel;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public RoundedTextFieldPanel getNameTextField() {
        return nameTextField;
    }

    public RoundedButton getSaveBtn() {
        return saveBtn;
    }

    public JScrollPane getScrollTable() {
        return scrollTable;
    }

    public JLabel getTitleScreen() {
        return titleScreen;
    }

    public JLabel getTitleTable() {
        return titleTable;
    }

    public RoundedTextAreaPanel getAddressTextArea() {
        return addressTextArea;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.xyz.roemahduren.presentation.component.input.RoundedTextAreaPanel addressTextArea;
    private com.xyz.roemahduren.presentation.component.table.Table branchTable;
    private com.xyz.roemahduren.presentation.component.panel.RoundedPanel formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private com.xyz.roemahduren.presentation.component.input.RoundedTextFieldPanel nameTextField;
    private com.xyz.roemahduren.presentation.component.RoundedButton saveBtn;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JLabel titleScreen;
    private javax.swing.JLabel titleTable;
    // End of variables declaration//GEN-END:variables
}
