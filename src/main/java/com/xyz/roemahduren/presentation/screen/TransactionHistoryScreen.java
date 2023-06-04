/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.xyz.roemahduren.presentation.screen;

import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.component.input.RoundedTextFieldPanel;
import com.xyz.roemahduren.presentation.component.table.Table;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class TransactionHistoryScreen extends javax.swing.JPanel {

    /**
     * Creates new form TransactionHistoryScreen
     */
    public TransactionHistoryScreen() {
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

        titleScreen = new JLabel();
        clearBtn = new RoundedButton();
        titleTable = new JLabel();
        searchCustomerTextField = new RoundedTextFieldPanel();
        searchBtn = new RoundedButton();
        scrollTable = new JScrollPane();
        transactionHistoryTable = new Table();

        setMinimumSize(new Dimension(800, 800));
        setPreferredSize(new Dimension(800, 800));
        setSize(new Dimension(800, 800));

        titleScreen.setFont(new Font("Helvetica Neue", 1, 24)); // NOI18N
        titleScreen.setForeground(new Color(2, 8, 38));
        titleScreen.setText("Manajemen Riwayat Transaksi");

        clearBtn.setBackground(new Color(221, 83, 83));
        clearBtn.setIcon(new ImageIcon(getClass().getResource("/images/Print.png"))); // NOI18N
        clearBtn.setText("Print Laporan");
        clearBtn.setBorderColor(new Color(221, 83, 83));
        clearBtn.setColor(new Color(221, 83, 83));
        clearBtn.setColorClick(new Color(204, 77, 77));
        clearBtn.setColorOver(new Color(204, 77, 77));
        clearBtn.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        titleTable.setFont(new Font("Helvetica Neue", 0, 22)); // NOI18N
        titleTable.setForeground(new Color(2, 8, 38));
        titleTable.setText("Daftar Riwayat Transaksi");

        searchCustomerTextField.setBackground(new Color(255, 255, 254));
        searchCustomerTextField.setLabelErrorText("");
        searchCustomerTextField.setLabelText("Cari");

        searchBtn.setText("Cari");
        searchBtn.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N

        transactionHistoryTable.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "#", "Nama Cabang", "Alamat Cabang", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTable.setViewportView(transactionHistoryTable);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTable, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleTable)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchCustomerTextField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleScreen)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titleScreen)
                    .addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(titleTable)
                    .addComponent(searchCustomerTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(18, 18, 18)
                .addComponent(scrollTable, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addGap(74, 74, 74))
        );
    }// </editor-fold>//GEN-END:initComponents

    public RoundedButton getClearBtn() {
        return clearBtn;
    }

    public JScrollPane getScrollTable() {
        return scrollTable;
    }

    public RoundedButton getSearchBtn() {
        return searchBtn;
    }

    public RoundedTextFieldPanel getSearchCustomerTextField() {
        return searchCustomerTextField;
    }

    public JLabel getTitleScreen() {
        return titleScreen;
    }

    public JLabel getTitleTable() {
        return titleTable;
    }

    public Table getTransactionHistoryTable() {
        return transactionHistoryTable;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RoundedButton clearBtn;
    private JScrollPane scrollTable;
    private RoundedButton searchBtn;
    private RoundedTextFieldPanel searchCustomerTextField;
    private JLabel titleScreen;
    private JLabel titleTable;
    private Table transactionHistoryTable;
    // End of variables declaration//GEN-END:variables
}
