package org.dam.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InitPanel extends JPanel {
    private JPanel mainPanel;
    private Image background;

    public InitPanel() {
        add(mainPanel);
        mainPanel.setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void setBackground(String path) {
        background = new ImageIcon(getClass().getResource(path)).getImage();
        repaint();
    }
    private void setCommand() {

    }

    public void addActionListeners(ActionListener listener) {

    }
}
