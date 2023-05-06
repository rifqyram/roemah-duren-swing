package com.xyz.roemahduren.presentation.component;

import com.xyz.roemahduren.presentation.theme.SystemColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        if (b) {
            setBackground(SystemColor.DISABLE_COLOR);
            setBorderColor(SystemColor.DISABLE_COLOR);
        } else {
            setBackground(getBackground());
            setBorderColor(getBorderColor());
        }
    }

    public RoundedButton() {
        setContentAreaFilled(false);
        setForeground(SystemColor.BUTTON_TEXT_COLOR);
        setBackground(SystemColor.SECONDARY_COLOR_BUTTON);
        color = SystemColor.SECONDARY_COLOR_BUTTON;
        borderColor = SystemColor.SECONDARY_COLOR_BUTTON;
        colorClick = SystemColor.SECONDARY_COLOR_ACTIVE_BUTTON;
        colorOver = SystemColor.SECONDARY_COLOR_HOVER_BUTTON;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(colorOver);
                setBorderColor(colorOver);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(color);
                setBorderColor(color);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
                setBorderColor(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                setBackground(color);
                setBorderColor(color);
            }
        });
    }

    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 8;

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}
