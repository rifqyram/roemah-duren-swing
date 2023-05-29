package com.xyz.roemahduren.util;

import com.xyz.roemahduren.presentation.component.RoundedButton;
import com.xyz.roemahduren.presentation.component.panel.DataEmpty;
import com.xyz.roemahduren.presentation.component.scroll.ScrollBar;

import javax.swing.*;
import java.awt.*;

import static com.xyz.roemahduren.presentation.theme.SystemColor.*;

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

    public static void clearPrimaryLoading(RoundedButton button, String defaultText) {
        button.setText(defaultText);
        button.setForeground(Color.WHITE);
        button.setEnabled(true);
        button.setBackground(PRIMARY_COLOR_BUTTON);
        button.setBorderColor(PRIMARY_COLOR_BUTTON);
    }

    public static void clearSecondaryLoading(RoundedButton button, String defaultText) {
        button.setText(defaultText);
        button.setForeground(Color.WHITE);
        button.setEnabled(true);
        button.setBackground(SECONDARY_COLOR_BUTTON);
        button.setBorderColor(SECONDARY_COLOR_BUTTON);
    }

    public static void setLoading(RoundedButton button) {
        button.setText("Loading...");
        button.setForeground(BUTTON_LOADING_TEXT_COLOR);
        button.setBackground(BUTTON_BORDER_LOADING_COLOR);
        button.setBorderColor(BUTTON_BORDER_LOADING_COLOR);
        button.setEnabled(false);
    }



}
