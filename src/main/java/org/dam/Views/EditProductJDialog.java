package org.dam.Views;


import javax.swing.*;
import java.awt.event.*;

public class EditProductJDialog extends JDialog {

    private JPanel mainPanel;
    private FormPanel formPanel;

    public FormPanel getFormPanel() {
        return formPanel;
    }

    public EditProductJDialog(FormPanel panel) {

        this.formPanel = panel;
        add(mainPanel);
        mainPanel.add(panel);
        setTitle("Edit Product");
        pack();
        setLocationRelativeTo(null);
    }

    public void showWindows() {
        setVisible(true);
    }

    public void closeWindows() {
        setVisible(false);
    }

    private void setCommand() {

    }




}
