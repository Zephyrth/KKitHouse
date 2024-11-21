package org.dam.Views;

import org.dam.Models.MuebleModel;

import javax.swing.*;
import java.awt.*;

public class ProductPanel extends JPanel {
    private JPanel mainPanel;
    private JLabel lb_img;
    private JLabel lb_nombre;
    private JLabel lb_cantidad;
    private JLabel lb_id;
    private JLabel lb_precio;
    private JLabel lb_fecha;
    private JLabel lb_exterior;
    private JLabel lb_marca;
    private JLabel lb_material;
    private MuebleModel mueble;

    public ProductPanel() {
        add(mainPanel);
    }

    public void setBackground(String rutaImagen) {
        ImageIcon icon = new ImageIcon(rutaImagen);
        Image imagenEscalada = icon.getImage().getScaledInstance(190, 190, Image.SCALE_SMOOTH);
        lb_img.setIcon(new ImageIcon(imagenEscalada));
    }

    public void setProduct(MuebleModel mueble) {
        this.mueble = mueble;
        lb_id.setText(String.valueOf(mueble.getId_Mueble()));
        lb_nombre.setText(mueble.getNombre());
        lb_fecha.setText(String.valueOf(mueble.getDate()));
        lb_marca.setText(mueble.getMarcaModel().getNombre());
        lb_material.setText(mueble.getMaterialModel().getNombre());
        lb_precio.setText(String.valueOf(mueble.getPrecio()));
        lb_cantidad.setText(String.valueOf(mueble.getStock()));
        lb_exterior.setText(mueble.isIs_Exterior() ? "SI" : "NO");
        setBackground(mueble.getImagenPath());

    }
}
