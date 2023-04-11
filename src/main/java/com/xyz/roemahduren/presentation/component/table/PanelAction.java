/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.xyz.roemahduren.presentation.component.table;

import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.event.TableActionEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 *
 * @author user
 */
public class PanelAction extends javax.swing.JPanel {

    /**
     * Creates new form PanelAction
     */
    public PanelAction() {
        initComponents();

    }

    public void initEvent(TableActionEvent event, int row) {
        roundedButton1.addActionListener(actionEvent -> event.onEdit(row));
        roundedButton2.addActionListener(actionEvent -> event.onDelete(row));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedButton1 = new RoundedButton();
        roundedButton2 = new RoundedButton();

        setBackground(new Color(255, 255, 254));

        roundedButton1.setBackground(new Color(255, 255, 254));
        roundedButton1.setForeground(new Color(102, 102, 102));
        roundedButton1.setIcon(new ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        roundedButton1.setText("Edit");
        roundedButton1.setBorderColor(new Color(255, 255, 254));
        roundedButton1.setColor(new Color(255, 255, 254));
        roundedButton1.setColorClick(new Color(204, 204, 204));
        roundedButton1.setColorOver(new Color(204, 204, 204));
        roundedButton1.setIconTextGap(8);
        roundedButton1.setMargin(new Insets(0, 0, 0, 0));

        roundedButton2.setForeground(new Color(102, 102, 102));
        roundedButton2.setIcon(new ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        roundedButton2.setText("Delete");
        roundedButton2.setToolTipText("");
        roundedButton2.setBorderColor(new Color(255, 255, 254));
        roundedButton2.setColor(new Color(255, 255, 254));
        roundedButton2.setColorClick(new Color(204, 204, 204));
        roundedButton2.setColorOver(new Color(204, 204, 204));
        roundedButton2.setMargin(new Insets(0, 0, 0, 0));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(roundedButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoundedButton roundedButton1;
    private RoundedButton roundedButton2;
    // End of variables declaration//GEN-END:variables
}
