package org.dam.Controllers;

import org.dam.Models.MuebleModel;
import org.dam.Utils.JTextFieldBorderColorUtil;
import org.dam.Views.FormPanel;
import org.dam.XML.XMLManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FormPanelController implements ActionListener, FocusListener, KeyListener {
    public static final String CREAR = "Crear", LIMPIAR = "Limpiar";
    private FormPanel formPanel;

    public FormPanelController(FormPanel formPanel) {
        this.formPanel = formPanel;
        handleComboBoxs();

    }

    private void handleCreateMueble() {
        try {
            XMLManager.createMueble(formPanel.getMueble());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void handleComboBoxs() {
        try {
            formPanel.loadCombos(XMLManager.getMarcaModelsForCombo(), XMLManager.getMarcaMaterialForCombo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleLostFocus(FocusEvent o) {
        Object source = o.getComponent();
        if (source.getClass().equals(JTextField.class) && ((JTextField) source).getText().equalsIgnoreCase("")) {
            JTextFieldBorderColorUtil.setJTextFieldBorderColorUtil((JTextField) source, Color.red);
        }
        if (((JTextField) source).getName().equalsIgnoreCase("idMueble") && !((JTextField) source).getText().equalsIgnoreCase("")) {
            try {
                MuebleModel muebleModel = XMLManager.getMueble(Integer.valueOf(((JTextField) source).getText().toString()));
                if (muebleModel != null) {
                    JTextFieldBorderColorUtil.setJTextFieldBorderColorUtil((JTextField) source, Color.red);
                    formPanel.setLb_warning("La id : " + ((JTextField) source).getText() + " ya existe.");
                } else {
                    JTextFieldBorderColorUtil.setJTextFieldBorderColorUtil((JTextField) source, Color.lightGray);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void handleGainFocus(FocusEvent o) {
        Object source = o.getComponent();
        if (source.getClass().equals(JTextField.class) && ((JTextField) source).getName().equalsIgnoreCase("idMueble")) {
            JTextFieldBorderColorUtil.setJTextFieldBorderColorUtil((JTextField) source, Color.lightGray);
        } else if (source.getClass().equals(JTextField.class) && ((JTextField) source).getName().equalsIgnoreCase("Name")) {
            JTextFieldBorderColorUtil.setJTextFieldBorderColorUtil((JTextField) source, Color.lightGray);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case CREAR:
                handleCreateMueble();
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        handleGainFocus(e);
    }
    @Override
    public void focusLost(FocusEvent e) {
        handleLostFocus(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
            
        if (!Character.isDigit(c)) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
