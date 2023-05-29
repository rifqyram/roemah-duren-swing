/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.xyz.roemahduren.presentation.component.table;

import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.event.TableActionEvent;
import com.xyz.roemahduren.presentation.event.TableActionSelectedEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class PanelActionSelect extends javax.swing.JPanel {

    /**
     * Creates new form PanelAction
     */
    public PanelActionSelect() {
        initComponents();
    }

    public PanelActionSelect(String text) {
        this();
        this.roundedButton.setText(text);
    }

    public PanelActionSelect(String textButton1, String textButton2) {
        this();
        this.roundedButton.setText(textButton2 == null ? roundedButton.getText() : textButton2);
    }

    public void initEvent(TableActionSelectedEvent event, int row) {
        roundedButton.addActionListener(actionEvent -> event.onSelected(row));
    }

    public RoundedButton getDeleteButton() {
        return roundedButton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedButton = new RoundedButton();

        setBackground(new Color(255, 255, 254));

        roundedButton.setForeground(new Color(130, 148, 96));
        roundedButton.setText("Beli");
        roundedButton.setToolTipText("");
        roundedButton.setBorderColor(new Color(255, 255, 254));
        roundedButton.setColor(new Color(255, 255, 254));
        roundedButton.setColorClick(new Color(204, 204, 204));
        roundedButton.setColorOver(new Color(204, 204, 204));
        roundedButton.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N
        roundedButton.setMargin(new Insets(0, 0, 0, 0));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(roundedButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(roundedButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoundedButton roundedButton;
    // End of variables declaration//GEN-END:variables
}
