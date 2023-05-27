package com.xyz.roemahduren.presentation.component.table;

import com.xyz.roemahduren.presentation.event.TableActionEvent;

import javax.swing.*;
import java.awt.*;

public class TableActionCellEditor extends DefaultCellEditor {
    private TableActionEvent event;
    String textButton1;
    String textButton2;


    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    public TableActionCellEditor(TableActionEvent event, String textButton1, String textButton2) {
        super(new JCheckBox());
        this.event = event;
        this.textButton1 = textButton1;
        this.textButton2 = textButton2;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (textButton1 == null && textButton2 == null) {
            PanelAction panelAction = new PanelAction();
            panelAction.initEvent(event, row);
            return panelAction;
        }
        PanelAction panelAction = new PanelAction(textButton1, textButton2);
        panelAction.initEvent(event, row);
        return panelAction;
    }
}
