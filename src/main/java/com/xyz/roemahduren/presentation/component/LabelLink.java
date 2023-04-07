package com.xyz.roemahduren.presentation.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LabelLink extends JLabel {

    private Color hover;
    private Color foreground;

    public LabelLink() {
        hover = new Color(0x6D7588);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setForeground(hover);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                setForeground(hover);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                setForeground(Color.black);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                setForeground(hover);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                setForeground(Color.black);
            }
        });
    }

    public Color getHover() {
        return hover;
    }

    public void setHover(Color hover) {
        this.hover = hover;
    }

}
