package org.dam.Controllers;

import org.dam.Models.MuebleModel;
import org.dam.Views.QueryPanel;
import org.dam.XML.XMLManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QueryPanelController implements ActionListener {
    private QueryPanel queryPanel;
    public static final String BYDATE="bydate";
    public QueryPanelController(QueryPanel queryPanel) {
        this.queryPanel = queryPanel;
        handleCargarTablaProductos();
    }

    private void handleCargarTablaProductos() {
        try {
            ArrayList<MuebleModel> productList;
            productList = XMLManager.getMuebles();
            if (!productList.isEmpty()) {
                queryPanel.setProductPanel(productList);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
