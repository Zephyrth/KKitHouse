package org.dam.Controllers;

import org.dam.Models.MuebleModel;
import org.dam.Utils.FileUtils;
import org.dam.Utils.JTextFieldBorderColorUtil;
import org.dam.Views.FormPanel;
import org.dam.XML.XMLManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static org.dam.XML.Exceptions.ERROR9;
import static org.dam.XML.XMLManager.getMueble;


public class FormPanelController implements ActionListener, FocusListener, KeyListener {
    public static final String CREAR = "Crear", LIMPIAR = "Limpiar";
    private FormPanel formPanel;

    public FormPanelController(FormPanel formPanel) {
        this.formPanel = formPanel;
        handleComboBoxs();

    }

    private void handleCreateMueble() {

        try {

            if (XMLManager.getMueble(formPanel.getMueble().getId_Mueble()) != null) {
                throw new Exception(ERROR9);
            }
        } catch (Exception e) {
            formPanel.setLb_warning(e.getMessage());
            return;
        }

        try {
            String rutaImagen;
            if (!formPanel.getImagenPanel().getRutaImagenOriginal().equals("src/images/default.png")) {
                rutaImagen = FileUtils.guardarImagen(formPanel.getImagenPanel().getRutaImagenOriginal(),
                        String.valueOf(formPanel.getMueble().getId_Mueble()));
            } else {
                rutaImagen = formPanel.getImagenPanel().getRutaImagenOriginal();
            }
            if (rutaImagen != null) {
                MuebleModel muebleModel = formPanel.getMueble();
                muebleModel.setImagenPath(rutaImagen);
                XMLManager.createMueble(muebleModel);
            }

        } catch (Exception e) {

            formPanel.setLb_warning(e.getMessage());
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
                MuebleModel muebleModel = getMueble(Integer.valueOf(((JTextField) source).getText().toString()));
                if (muebleModel != null) {
                    JTextFieldBorderColorUtil.setJTextFieldBorderColorUtil((JTextField) source, Color.red);
                    formPanel.setLb_warning("La id : " + ((JTextField) source).getText() + " ya existe.");
                } else {
                    JTextFieldBorderColorUtil.setJTextFieldBorderColorUtil((JTextField) source, Color.lightGray);
                    formPanel.setLb_warning("");
                }
            } catch (NumberFormatException e) {
                formPanel.setLb_warning("La id: " + ((JTextField) source).getText() + " supera los 7 digitos.");
            } catch (Exception ex) {
                formPanel.setLb_warning(ex.getMessage());
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

    private void clearFormPanel() {
        formPanel.setLb_warning("");
        formPanel.initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case CREAR:
                handleCreateMueble();
                break;
            case LIMPIAR: {
                clearFormPanel();
                break;
            }
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
