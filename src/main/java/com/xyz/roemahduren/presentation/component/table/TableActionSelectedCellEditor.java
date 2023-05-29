package com.xyz.roemahduren.presentation.component.table;

import com.xyz.roemahduren.presentation.event.TableActionSelectedEvent;

import javax.swing.*;
import java.awt.*;

public class TableActionSelectedCellEditor extends DefaultCellEditor {
    private TableActionSelectedEvent event;
    String textButton1;


    public TableActionSelectedCellEditor(TableActionSelectedEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    public TableActionSelectedCellEditor(TableActionSelectedEvent event, String textButton1) {
        super(new JCheckBox());
        this.event = event;
        this.textButton1 = textButton1;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (textButton1 == null) {
            PanelActionSelect panelAction = new PanelActionSelect();
            panelAction.initEvent(event, row);
            return panelAction;
        }
        PanelActionSelect panelAction = new PanelActionSelect(textButton1);
        panelAction.initEvent(event, row);
        return panelAction;
    }
}
