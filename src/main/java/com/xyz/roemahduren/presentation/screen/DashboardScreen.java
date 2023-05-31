package com.xyz.roemahduren.presentation.screen;

import com.xyz.roemahduren.presentation.component.panel.RoundedPanel;
import com.xyz.roemahduren.presentation.component.table.Table;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class DashboardScreen extends JPanel {

    /**
     * Creates new form DashboardScreen
     */
    public DashboardScreen() {
        initComponents();  
        table1.fixTable(jScrollPane1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new JLabel();
        roundedPanel1 = new RoundedPanel();
        jScrollPane1 = new JScrollPane();
        table1 = new Table();

        setBackground(new Color(255, 255, 254));
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));

        welcomeLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        welcomeLabel.setText("Hello Admin!");

        roundedPanel1.setBackground(new Color(245, 245, 245));
        roundedPanel1.setCornerRadius(8);

        GroupLayout roundedPanel1Layout = new GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 671, Short.MAX_VALUE)
        );
        roundedPanel1Layout.setVerticalGroup(roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );

        jScrollPane1.setBackground(new Color(243, 244, 245));
        jScrollPane1.setToolTipText("");
        jScrollPane1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        table1.setBackground(new Color(245, 245, 245));
        table1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        table1.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Name", "Product", "Price", "Quantity", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(table1);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(welcomeLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                    .addComponent(roundedPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(welcomeLabel)
                .addGap(37, 37, 37)
                .addComponent(roundedPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 65, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JScrollPane jScrollPane1;
    private RoundedPanel roundedPanel1;
    private Table table1;
    private JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
