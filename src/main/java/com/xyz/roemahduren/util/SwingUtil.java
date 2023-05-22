package com.xyz.roemahduren.util;

import com.xyz.roemahduren.presentation.component.DataEmpty;
import com.xyz.roemahduren.presentation.component.scroll.ScrollBar;
import com.xyz.roemahduren.presentation.component.table.TableActionCellEditor;
import com.xyz.roemahduren.presentation.component.table.TableActionCellRender;
import com.xyz.roemahduren.presentation.event.TableActionEvent;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class SwingUtil {

    public static void centerWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public static void hideComponentTimer(Component component, int millis) {
        Timer timer = new Timer(millis, e -> component.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }

    public static void setActionTable(JTable table, String[] columns, TableActionEvent tableActionEvent) {
        table.getColumnModel().getColumn(columns.length - 1).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(columns.length - 1).setCellEditor(new TableActionCellEditor(tableActionEvent));
    }

    public static void fixScroll(JScrollPane scroll) {
        scroll.setBorder(null);
        scroll.setVerticalScrollBar(new ScrollBar());
        scroll.setHorizontalScrollBar(new ScrollBar());

        scroll.getHorizontalScrollBar().setBackground(Color.WHITE);
        scroll.getVerticalScrollBar().setBackground(Color.WHITE);
        scroll.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();

        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public static void setEmptyState(JScrollPane scrollPane) {
        DataEmpty dataEmpty = new DataEmpty();
        scrollPane.setViewportView(dataEmpty);
    }

}
