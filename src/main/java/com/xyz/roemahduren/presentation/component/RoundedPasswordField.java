package com.xyz.roemahduren.presentation.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.Array;
import java.util.Arrays;

public class RoundedPasswordField extends JPasswordField implements FocusListener {
    private Shape shape;
    private int cornerRadius = 8;
    private String placeholder;
    private boolean isPlaceholderVisible;

    public RoundedPasswordField() {
        setOpaque(false);
        setMargin(new Insets(4, 6, 4, 6));
        setForeground(new Color(0x6D7588));

        isPlaceholderVisible = true;

        if (placeholder != null) {
            setText(placeholder);
        }

        addFocusListener(this);
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public String getStringPassword() {
        String password = new String(getPassword());
        if (getPassword().length == 0) return "";
        if (password.equals(placeholder)) return "";
        return password;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        this.setText(placeholder);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        if (isPlaceholderVisible && !getStringPassword().isEmpty() && placeholder != null) {
            g.setColor(getForeground());
            g.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
        }

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

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (isPlaceholderVisible) {
            setText("");
            setForeground(Color.BLACK);
            isPlaceholderVisible = false;
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (getStringPassword().isEmpty()) {
            setForeground(new Color(0x6D7588));
            setText(placeholder);
            isPlaceholderVisible = true;
        }
    }
}
