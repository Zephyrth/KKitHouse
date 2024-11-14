package org.dam.Controllers;

import org.dam.Utils.FileUtils;
import org.dam.Views.ImagenPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ImagenPanelController implements ActionListener {

    private final ImagenPanel imagenPanel;
    public static final String SELECT = "SELECT", CLEAR = "CLEAR";

    public ImagenPanelController(ImagenPanel imagenPanel) {
        this.imagenPanel = imagenPanel;
        setDefaultBackgroundImage();
    }

    public void setDefaultBackgroundImage() {
        try {
            String rutaImagen = URLDecoder.decode(getClass().getResource("/default.png").getPath(), "UTF-8");
            imagenPanel.setBackground(rutaImagen);
            imagenPanel.setRutaImagenOriginal(rutaImagen);
        } catch (UnsupportedEncodingException e) {
            System.out.println("No se encontró el recurso indicado");
        }
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
}
