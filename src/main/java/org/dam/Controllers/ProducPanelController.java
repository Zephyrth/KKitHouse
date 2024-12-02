package org.dam.Controllers;

import org.dam.Views.*;
import org.dam.XML.XMLManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static org.dam.Controllers.FormPanelController.ACTUALIZAR;

public class ProducPanelController implements MouseListener {
    private ProductPanel panel;
    private EditProductJDialog productDialog;
    public ProducPanelController(ProductPanel panel, FormPanel formPanel) {
        this.panel = panel;
    }

    private void handleShowEditProduct() {
        FormPanel formEdit = new FormPanel();
        FormPanelController formPanelController = new FormPanelController(formEdit);
        ImagenPanel imagenPanel = formEdit.getImagenPanel();
        ImagenPanelController imagenPanelController = new ImagenPanelController(imagenPanel);
        imagenPanel.addActionListeners(imagenPanelController);
        formEdit.addActionListeners(formPanelController);
        formEdit.setModel(ACTUALIZAR);
        formEdit.setBackground("/backgroundForm.png");
        formEdit.setMueble(panel.getMueble());
        formEdit.getImagenPanel().setRutaImagenOriginal(panel.getMueble().getImagenPath());
        productDialog = new EditProductJDialog(formEdit);
        productDialog.showWindows();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        String[] valores = new String[]{"EDITAR", "BORRAR"};
        if (e.getClickCount() == 2) {
            int rec = JOptionPane.showOptionDialog(null, "Que desea realizar", "Modo Edición", 0, 2,
                    null, valores, null);
            if (rec == 0) {
                handleShowEditProduct();
            } else if (rec == 1) {
                int response = JOptionPane.showConfirmDialog(null, "¿Realmente quieres borrarlo?");
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        XMLManager.removeById(String.valueOf(panel.getMueble().getId_Mueble()));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    System.out.println("no se borra");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
