package com.xyz.roemahduren.presentation.component.input;

import com.xyz.roemahduren.presentation.theme.SystemColor;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class NumberFormattedField extends JFormattedTextField implements FocusListener {

    private Shape shape;
    private int cornerRadius = 8;
    private String placeholder;
    private boolean isPlaceholderVisible;

    public NumberFormattedField() {
        setOpaque(false);
        setMargin(new Insets(4, 6, 4, 6));
        setForeground(SystemColor.borderColor);
        format();

        isPlaceholderVisible = true;

        if (placeholder != null) {
            setText(placeholder);
        }

        addFocusListener(this);
    }

    private void format() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0' && c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    public String getText() {
        String text = super.getText();
        if (text.equals(placeholder)) return "";
        return text;
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        if (t.length() > 0 && !t.equals(placeholder)) {
            this.isPlaceholderVisible = false;
        }
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

        if (isPlaceholderVisible && !getText().isEmpty() && placeholder != null) {
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
        if (getText().isEmpty()) {
            setForeground(new Color(0x6D7588));
            setText(placeholder);
            isPlaceholderVisible = true;
        }
    }
}
