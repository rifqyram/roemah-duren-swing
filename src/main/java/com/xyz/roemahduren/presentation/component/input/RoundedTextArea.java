package com.xyz.roemahduren.presentation.component.input;

import com.xyz.roemahduren.presentation.component.scroll.ScrollBar;
import com.xyz.roemahduren.presentation.theme.SystemColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextArea extends JTextArea {
    private Shape shape;
    private int cornerRadius = 8;

    public RoundedTextArea() {
        setOpaque(false);
        setMargin(new Insets(4, 6, 4, 6));
        setForeground(SystemColor.BORDER_COLOR);
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 11, getHeight() - 1, cornerRadius, cornerRadius);
        }
        return shape.contains(x, y);
    }

    public void fixArea(JScrollPane scroll) {
        scroll.setBorder(null);
        scroll.setVerticalScrollBar(new ScrollBar());
        scroll.getVerticalScrollBar().setBackground(Color.WHITE);
        scroll.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        setLineWrap(true);
        setWrapStyleWord(true);
    }
}
