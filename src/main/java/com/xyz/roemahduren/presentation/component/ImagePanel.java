package com.xyz.roemahduren.presentation.component;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private ImageIcon image;

    public ImagePanel() {
        super();
    }

    public void setImage(Icon icon) {
        this.image = (ImageIcon) icon;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g.create();

        if (image != null) {
            graphics2D.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
        }

        graphics2D.dispose();
    }
}
