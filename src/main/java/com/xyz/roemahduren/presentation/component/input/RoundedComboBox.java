package com.xyz.roemahduren.presentation.component.input;

import com.xyz.roemahduren.presentation.theme.SystemColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedComboBox<E> extends JComboBox<E> {

    private Shape shape;
    private int cornerRadius = 8;

    public RoundedComboBox() {
        setUI(new ComboBoxUI());
        setForeground(SystemColor.borderColor);
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
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

}
