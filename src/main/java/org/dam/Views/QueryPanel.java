package org.dam.Views;

import org.dam.Models.MuebleModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QueryPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel productList;
    private JPanel queryPanel;

    public QueryPanel() {
        add(mainPanel);
        setCommand();
        productList.setLayout(new BoxLayout(productList, BoxLayout.Y_AXIS));
    }

    public void setProductPanel(ArrayList<MuebleModel> muebles) {
        for (MuebleModel muebleModel : muebles) {
            ProductPanel productPanel = new ProductPanel();
            productPanel.setProduct(muebleModel);
            productList.add(productPanel);
        }

    }


    private void setCommand() {


    }

    // llamar desde el main
    public void addListeners(ActionListener listener) {


    }
}
