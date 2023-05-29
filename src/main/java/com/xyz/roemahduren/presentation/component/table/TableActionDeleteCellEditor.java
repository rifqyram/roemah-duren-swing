package com.xyz.roemahduren.presentation.component.table;

import com.xyz.roemahduren.presentation.event.TableActionSelectedEvent;

import javax.swing.*;
import java.awt.*;

public class TableActionDeleteCellEditor extends DefaultCellEditor {
    private TableActionSelectedEvent event;
    String textButton1;


    public TableActionDeleteCellEditor(TableActionSelectedEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    public TableActionDeleteCellEditor(TableActionSelectedEvent event, String textButton1) {
        super(new JCheckBox());
        this.event = event;
        this.textButton1 = textButton1;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (textButton1 == null) {
            PanelActionDelete panelAction = new PanelActionDelete();
            panelAction.initEvent(event, row);
            return panelAction;
        }
        PanelActionDelete panelAction = new PanelActionDelete(textButton1);
        panelAction.initEvent(event, row);
        return panelAction;
    }
}
