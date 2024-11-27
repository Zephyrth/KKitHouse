package org.dam.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.dam.Controllers.ImagenPanelController.CLEAR;
import static org.dam.Controllers.ImagenPanelController.SELECT;

public class ImagenPanel extends JPanel {
    private JPanel mainPanel;
    private JButton btn_seleccionar;
    private JButton btn_clear;
    private JLabel lb_img;
    private String rutaImagenOriginal;

    public ImagenPanel() {
        add(mainPanel);
        setRutaImagenOriginal("default");
        setCommand();
    }


    public String getRutaImagenOriginal() {
        return rutaImagenOriginal;
    }

    public void setRutaImagenOriginal(String rutaImagenOriginal) {
        this.rutaImagenOriginal = rutaImagenOriginal;
    }

    public void setBackground(String rutaImagen) {
        if (rutaImagen.equals("default")) {
            setDefaultBackgroundImage();
            return;
        }
        // Crea un nuevo objeto ImageIcon a partir de la ruta de la imagen proporcionada.
        ImageIcon icon = new ImageIcon(rutaImagen);

        // Obtiene la imagen del ImageIcon y la escala a 280x230 píxeles utilizando un algoritmo de suavizado para una mejor calidad visual.
        Image imagenEscalada = icon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);

        // Asigna la imagen escalada a un nuevo ImageIcon y lo establece en el componente imageLabel.
        lb_img.setIcon(new ImageIcon(imagenEscalada));
    }

    public void setDefaultBackgroundImage() {
        Image imagenEscalada = new ImageIcon(getClass().getResource("/default.png")).getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        lb_img.setIcon(new ImageIcon(imagenEscalada));
    }

    private void setCommand() {
        btn_seleccionar.setActionCommand(SELECT);
        btn_clear.setActionCommand(CLEAR);

    }

    public void addActionListeners(ActionListener listener) {
        btn_clear.addActionListener(listener);
        btn_seleccionar.addActionListener(listener);

    }
}
