package org.dam.Controllers;

import org.dam.Models.MarcaModel;
import org.dam.Models.MuebleModel;
import org.dam.Views.QueryPanel;
import org.dam.XML.XMLManager;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class QueryPanelController implements ActionListener, ItemListener, KeyListener {
    private QueryPanel queryPanel;
    private ArrayList<MuebleModel> muebleModels = new ArrayList<>();
    public static final String BYDATE = "bydate",RESET = "reset";


    public QueryPanelController(QueryPanel queryPanel) {
        this.queryPanel = queryPanel;
        handleCargarTablaProductos();
        handleComboBoxs();

    }
    private void handleComboBoxs() {
        try {
            queryPanel.loadCombos(XMLManager.getMarcaModelsForCombo());
            queryPanel.setSelected(-1);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
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

    private void handleGetProductoByDate() {
        HashMap<String, LocalDate> fecha = null;
        fecha = queryPanel.getFechas();
        if (fecha == null) {
            JOptionPane.showMessageDialog(null, "No puedes buscar sin ninguna fecha.");
        } else {
            if (fecha.get("inicio").isBefore(fecha.get("final"))||fecha.get("final").isEqual(fecha.get("inicio"))) {
                try {
                    ArrayList<MuebleModel> productList;
                    productList = XMLManager.getMueblesByDate(fecha.get("inicio"), fecha.get("final"));
                    if (!productList.isEmpty()) {
                        queryPanel.getProductList().removeAll();
                        queryPanel.setProductPanel(productList);
                        queryPanel.getProductList().revalidate();
                    }else {
                        JOptionPane.showMessageDialog(null, "No se encontro productos en las fechas introducidas.");

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Porfavor introduzca las fechas correspondidas");
            }
        }
    }
    private void handleInitForm(){
        queryPanel.getProductList().removeAll();
        handleCargarTablaProductos();
        queryPanel.getProductList().revalidate();
        queryPanel.setSelected(-1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case BYDATE: {
                handleGetProductoByDate();
                break;
            }
            case RESET: {
                handleInitForm();
            }
            default: {
                break;
            }
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            ArrayList<MuebleModel> productList= new ArrayList<>();
            MarcaModel marcaModel = (MarcaModel) e.getItem();
            if (!muebleModels.isEmpty()) {
                for (MuebleModel muebleModel : muebleModels) {
                    if (muebleModel.getMarcaModel().equals(marcaModel)) {
                        productList.add(muebleModel);
                    }
                }
                if (productList.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"No se encontro muebles con esas especificaciónes.");
                    queryPanel.clerTextAll();
                    handleInitForm();
                }else {
                    queryPanel.getProductList().removeAll();
                    queryPanel.setProductPanel(productList);
                    queryPanel.getProductList().revalidate();
                }
            }else {
                JOptionPane.showMessageDialog(null,"Busque por palabra clave antes.");

            }


        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            if (queryPanel.getTextAll().equals("")) {
                handleInitForm();

            }else {
                muebleModels = XMLManager.getMueblesByTxtField(queryPanel.getTextAll());
                queryPanel.getProductList().removeAll();
                if (!muebleModels.isEmpty()) {
                    queryPanel.setProductPanel(muebleModels);
                }else {
                    JOptionPane.showMessageDialog(null,"No se encontro muebles con esas especificaciónes.");
                    queryPanel.setProductPanel(XMLManager.getMuebles());
                }
                queryPanel.getProductList().revalidate();
            }



        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
