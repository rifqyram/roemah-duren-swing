package com.xyz.roemahduren.presentation.component.table;


import com.xyz.roemahduren.presentation.component.scroll.ScrollBar;
import com.xyz.roemahduren.presentation.theme.SystemColor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                if (jtable.getColumnCount() - 1 == i1 && jtable.getColumnName(jtable.getColumnCount() - 1).equalsIgnoreCase("Aksi")) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder);
                if (selected) {
                    com.setForeground(SystemColor.BORDER_COLOR);
                } else {
                    com.setForeground(new Color(102, 102, 102));
                }
                return com;
            }
        });
    }

    public void fixTable(JScrollPane scroll) {
        scroll.setBorder(null);
        scroll.setVerticalScrollBar(new ScrollBar());
        scroll.setHorizontalScrollBar(new ScrollBar());
        scroll.getHorizontalScrollBar().setBackground(Color.WHITE);
        scroll.getVerticalScrollBar().setBackground(Color.WHITE);
        scroll.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    @Override
    public boolean getScrollableTracksViewportWidth()
    {
        return getPreferredSize().width < getParent().getWidth();
    }

    @Override
    public void doLayout()
    {
        TableColumn resizingColumn = null;

        if (tableHeader != null)
            resizingColumn = tableHeader.getResizingColumn();

        if (resizingColumn == null)
        {
            setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            super.doLayout();
        }

        else
        {
            TableColumnModel tcm = getColumnModel();

            for (int i = 0; i < tcm.getColumnCount(); i++)
            {
                TableColumn tc = tcm.getColumn(i);
                tc.setPreferredWidth( tc.getWidth() );
            }

            if (tcm.getTotalColumnWidth() < getParent().getWidth())
                setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            super.doLayout();
        }

        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
}
