package com.xyz.roemahduren.presentation.component.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String columnName = table.getColumnName(column);
        TableColumn column1 = table.getColumn(columnName);
        column1.setPreferredWidth(200);
        return new PanelAction();
    }
}
