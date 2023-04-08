package com.xyz.roemahduren.presentation.screen;

import com.xyz.roemahduren.presentation.component.panel.RoundedPanel;
import com.xyz.roemahduren.presentation.component.table.Table;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
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

        jLabel1 = new JLabel();
        roundedPanel1 = new RoundedPanel();
        tablePanel = new RoundedPanel();
        jScrollPane1 = new JScrollPane();
        table1 = new Table();

        setPreferredSize(new Dimension(800, 800));
        setSize(new Dimension(800, 800));

        jLabel1.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Hello Admin!");

        roundedPanel1.setBackground(new Color(255, 255, 255));
        roundedPanel1.setCornerRadius(8);

        GroupLayout roundedPanel1Layout = new GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );
        roundedPanel1Layout.setVerticalGroup(roundedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );

        tablePanel.setBackground(new Color(255, 255, 255));
        tablePanel.setCornerRadius(8);

        table1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        table1.setModel(new DefaultTableModel(
            new Object [][] {
                {"1", "Rifqi", "Topi", "50000", "2", "1000000"},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
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
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(table1);

        GroupLayout tablePanelLayout = new GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(tablePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(16, 16, 16))
        );
        tablePanelLayout.setVerticalGroup(tablePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(roundedPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private RoundedPanel roundedPanel1;
    private Table table1;
    private RoundedPanel tablePanel;
    // End of variables declaration//GEN-END:variables
}
