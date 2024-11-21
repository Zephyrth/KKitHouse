package org.dam.Views;

import com.github.lgooddatepicker.components.DatePicker;
import org.dam.Models.MarcaModel;
import org.dam.Models.MaterialModel;
import org.dam.Models.MuebleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import static org.dam.Controllers.FormPanelController.CREAR;
import static org.dam.Controllers.FormPanelController.LIMPIAR;


public class FormPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel imagenPreviewPanel;
    private JTextField tx_idMueble;
    private JTextField tx_nombre;
    private JSlider sl_precio;
    private JTextField tx_cantidad;
    private JTextField tx_precio;
    private JLabel lb_euro;
    private JComboBox<MarcaModel> cb_marca;
    private JCheckBox chk_exterior;
    private JButton btn_create;
    private JButton btn_clear;
    private JLabel lb_marca;
    private JLabel lb_material;
    private JLabel lb_fecha;
    private JList<MaterialModel> list_material;
    private DatePicker dp_fecha;
    private JLabel lb_cantidad;
    private JLabel lb_precio;
    private JLabel lb_nombre;
    private JLabel lb_id;
    private JLabel lb_warning;
    private ImagenPanel imagenPanel;
    private Image background;

    public FormPanel() {
        add(mainPanel);
        setCommand();
        mainPanel.setOpaque(false);
        setImagenPanel();
        initComponents();
    }


    public void setLb_warning(String warning) {
        this.lb_warning.setText(warning);
    }

    public MuebleModel getMueble() throws Exception {
        MuebleModel mueble = new MuebleModel();
        if (tx_idMueble.getText().toString().equals("")
                || tx_nombre.getText().toString().equals("")
                || tx_cantidad.getText().toString().equals("")
                || dp_fecha.getText().toString().equals("")
                || list_material.getSelectedIndex() == -1) {
            throw new Exception("NO DEJE NINGÚN CAMPO VACIO");
        } else if (tx_idMueble.getText().toString().length() > 7) {
            throw new Exception("EL ID NO DEBE SER MAS DE 7 CARACTERES");
        }
        mueble.setId_Mueble(Integer.parseInt(tx_idMueble.getText()));
        mueble.setNombre(tx_nombre.getText());
        mueble.setPrecio(Double.parseDouble(tx_precio.getText()));
        mueble.setIs_Exterior(chk_exterior.isSelected());
        mueble.setDate(dp_fecha.getDate());
        mueble.setStock(Integer.parseInt(tx_cantidad.getText()));
        mueble.setMaterialModel(list_material.getSelectedValue());
        MarcaModel marca;
        marca = (MarcaModel) cb_marca.getSelectedItem();
        mueble.setMarcaModel(marca);
        MaterialModel material;
        material = list_material.getSelectedValue();
        mueble.setMaterialModel(material);
        return mueble;
    }

    public void loadCombos(ArrayList<MarcaModel> marca, ArrayList<MaterialModel> material) {
        cb_marca.setModel(new DefaultComboBoxModel(marca.toArray()));
        list_material.setModel(new DefaultComboBoxModel(material.toArray()));
    }

    public ImagenPanel getImagenPanel() {
        return imagenPanel;
    }

    private void setImagenPanel() {
        imagenPanel = new ImagenPanel();
        imagenPreviewPanel.add(imagenPanel);
        imagenPanel.setRutaImagenOriginal("src/images/default.png");
    }

    public void setBackground(String path) {
        background = new ImageIcon(getClass().getResource(path)).getImage();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void setCommand() {
        btn_create.setActionCommand(CREAR);
        btn_clear.setActionCommand(LIMPIAR);
    }

    public void addActionListeners(ActionListener listener) {
        btn_create.addActionListener(listener);
        btn_clear.addActionListener(listener);
        tx_idMueble.addFocusListener((FocusListener) listener);
        tx_nombre.addFocusListener((FocusListener) listener);
        tx_idMueble.addKeyListener((KeyListener) listener);
        tx_precio.addKeyListener((KeyListener) listener);
        tx_cantidad.addKeyListener((KeyListener) listener);
    }

    public void initComponents() {
        tx_idMueble.setName("idMueble");
        tx_nombre.setName("Name");
        tx_precio.setText("500");
        sl_precio.setMaximum(1000);
        sl_precio.setMinimum(0);
        sl_precio.setValue(500);
        tx_idMueble.setText("");
        tx_nombre.setText("");
        tx_cantidad.setText("");
        dp_fecha.setDate(null);
        String rutaImagen = null;
        try {
            rutaImagen = URLDecoder.decode(getClass().getResource("/default.png").getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        imagenPanel.setBackground(rutaImagen);
        imagenPanel.setRutaImagenOriginal(rutaImagen);


    }

}
