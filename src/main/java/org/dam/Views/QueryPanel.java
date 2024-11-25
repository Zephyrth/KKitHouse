package org.dam.Views;

import org.dam.Controllers.ProducPanelController;
import org.dam.Models.MuebleModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static org.dam.Controllers.QueryPanelController.BYDATE;

public class QueryPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel productList;
    private JPanel queryPanel;
    private JButton btn_byDate;
    private JTextField tx_all;
    private JComboBox cb_marca;
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
