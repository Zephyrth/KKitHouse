package org.dam.Views;

import com.github.lgooddatepicker.components.DatePicker;

import org.dam.Controllers.ProducPanelController;
import org.dam.Models.MarcaModel;
import org.dam.Models.MaterialModel;
import org.dam.Models.MuebleModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.dam.Controllers.QueryPanelController.BYDATE;

public class QueryPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel productList;
    private JPanel queryPanel;
    private JButton btn_byDate;
    private JTextField tx_all;

    public JPanel getProductList() {
        return productList;
    }

    private JComboBox cb_marca;
    private DatePicker dp_inicio;
    private DatePicker dp_final;
    private FormPanel formPanel;

    public QueryPanel(FormPanel formPanel) {
        add(mainPanel);
        setCommand();
        productList.setLayout(new BoxLayout(productList, BoxLayout.Y_AXIS));
        this.formPanel = formPanel;
    }

    public void setProductPanel(ArrayList<MuebleModel> muebles) {
        for (MuebleModel muebleModel : muebles) {
            ProductPanel productPanel = new ProductPanel();
            ProducPanelController producPanelController = new ProducPanelController(productPanel, formPanel);
            productPanel.addListeners(producPanelController);
            productPanel.setProduct(muebleModel);
            productList.add(productPanel);
        }
    }
    public String getTextAll(){
        return tx_all.getText();
    }
    public void loadCombos(ArrayList<MarcaModel> marca) {
        cb_marca.setModel(new DefaultComboBoxModel(marca.toArray()));

    }
    public void setSelected(int i){
        cb_marca.setSelectedIndex(i);
    }

    public HashMap<String, LocalDate> getFechas() {
        HashMap<String, LocalDate> fechas = new HashMap<>();
        if (dp_inicio == null || dp_inicio.getDate() == null) {
            return null;
        }else {
            fechas.put("inicio", dp_inicio.getDate());
            fechas.put("final", dp_final.getDate());
        }
        return fechas;
    }

    private void setCommand() {
        btn_byDate.setActionCommand(BYDATE);
    }

    // llamar desde el main
    public void addListeners(ActionListener listener) {
        btn_byDate.addActionListener(listener);
        cb_marca.addItemListener((ItemListener) listener);
        tx_all.addKeyListener((KeyListener) listener);
    }
}
