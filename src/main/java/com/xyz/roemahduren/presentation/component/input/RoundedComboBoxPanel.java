/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.xyz.roemahduren.presentation.component.input;

import javax.swing.*;
import java.awt.*;

/**
 * @author user
 */
public class RoundedComboBoxPanel<T> extends javax.swing.JPanel {

    /**
     * Creates new form RoundedComboBoxPanel
     */
    public RoundedComboBoxPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new JLabel();
        errorLabel = new JLabel();
        comboBox = new RoundedComboBox<>();

        label.setForeground(new Color(95, 108, 123));
        label.setText("label");

        errorLabel.setForeground(new Color(220, 53, 69));
        errorLabel.setText("error");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(comboBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(errorLabel)
                                .addComponent(label))
                        .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(label)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorLabel)
                        .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    public String getLabelText() {
        return label.getText();
    }

    public void setLabelText(String text) {
        this.label.setText(text);
    }

    public String getLabelErrorText() {
        return errorLabel.getText();
    }

    public void setLabelErrorText(String text) {
        this.errorLabel.setText(text);
    }


    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JLabel getLabel() {
        return label;
    }

    public RoundedComboBox<T> getComboBox() {
        return comboBox;
    }

    public Object getValue() {
        return comboBox.getSelectedItem();
    }

    public void setValue(Object value) {
        this.comboBox.setSelectedItem(value);
    }

    public String getErrorMessage() {
        return this.errorLabel.getText();
    }

    public void setErrorMessage(String text) {
        this.errorLabel.setText(text);
    }

    public void clearErrorMessage() {
        this.errorLabel.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoundedComboBox<T> comboBox;
    private JLabel errorLabel;
    private JLabel label;
    // End of variables declaration//GEN-END:variables
}
