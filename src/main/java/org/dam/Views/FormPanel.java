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
import java.util.ArrayList;

import static org.dam.Controllers.FormPanelController.CREAR;
import static org.dam.Controllers.FormPanelController.LIMPIAR;
import static org.dam.Controllers.MainFrameController.NAVIGATE_CREATE;
import static org.dam.Controllers.MainFrameController.NAVIGATE_INICIO;

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

    public JTextField getTx_idMueble() {
        return tx_idMueble;
    }

    public JTextField getTx_nombre() {
        return tx_nombre;
    }

    public void setLb_warning(String warning) {
        this.lb_warning.setText(warning);
    }

    public MuebleModel getMueble() {
        MuebleModel mueble = new MuebleModel();
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

    }

}
