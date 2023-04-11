package com.xyz.roemahduren.presentation.component.table;

import com.xyz.roemahduren.presentation.event.TableActionEvent;

import javax.swing.*;
import java.awt.*;

public class TableActionCellEditor extends DefaultCellEditor {
    private TableActionEvent event;
    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelAction panelAction = new PanelAction();
        panelAction.initEvent(event, row);
        return panelAction;
    }
}
