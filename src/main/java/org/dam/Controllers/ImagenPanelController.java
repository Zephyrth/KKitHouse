package org.dam.Controllers;

import org.dam.Utils.FileUtils;
import org.dam.Views.ImagenPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ImagenPanelController implements ActionListener, MouseListener {

    private final ImagenPanel imagenPanel;
    public static final String SELECT = "SELECT", CLEAR = "CLEAR";

    public ImagenPanelController(ImagenPanel imagenPanel) {
        this.imagenPanel = imagenPanel;
        setDefaultBackgroundImage();
    }

    public void setDefaultBackgroundImage() {
        imagenPanel.setBackground("default");
        imagenPanel.setRutaImagenOriginal("default");
    }

    private void handleSelectImage() {
        String path = FileUtils.seleccionarRutaImagen();
        if (path != null) {
            imagenPanel.setBackground(path);
            imagenPanel.setRutaImagenOriginal(path);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case SELECT: {
                handleSelectImage();
                break;
            }
            case CLEAR: {
                setDefaultBackgroundImage();
                break;
            }
            default: {
                System.out.println("Error Command " + command);
                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            handleSelectImage();
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
