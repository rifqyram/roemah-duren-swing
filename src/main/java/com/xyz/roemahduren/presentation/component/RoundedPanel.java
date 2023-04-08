package com.xyz.roemahduren.presentation.component;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius = 30;

    public RoundedPanel() {
        super();
        setOpaque(false);

    }

    public RoundedPanel(int radius) {
        super();
        setOpaque(false);
        this.cornerRadius = radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setColor(getForeground());
        super.paintComponent(g);
    }

    // Getters and setters for the corner radius
    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint(); // Redraw the panel with the new corner radius
    }
}
