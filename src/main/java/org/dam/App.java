package org.dam;

import com.formdev.flatlaf.FlatDarculaLaf;
import org.dam.Controllers.FormPanelController;
import org.dam.Controllers.ImagenPanelController;
import org.dam.Controllers.MainFrameController;
import org.dam.Controllers.QueryPanelController;
import org.dam.Models.MarcaModel;
import org.dam.Models.MaterialModel;
import org.dam.Models.MuebleModel;
import org.dam.Views.EditProductJDialog;
import org.dam.Views.MainFrame;
import org.dam.Views.QueryPanel;
import org.dam.XML.XMLManager;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        try {
//            XMLManager.createMueble(new MuebleModel(1, 1, 2.0, "nombre",
//                    new MaterialModel(2, "test", 123.3),
//                     new MarcaModel(1, "test"), LocalDate.now(), false));
//            System.out.println(XMLManager.getMueble(1));
//
//            XMLManager.updateMueble(1, new MuebleModel(1, 2.0, "s",
//                    new MaterialModel(1, "test", 123.3),
//                    new MarcaModel(1, "test"), LocalDate.now(), false));
//            System.out.println(XMLManager.getMueble(1));
//            XMLManager.removeMuebleById("1","");
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }

        FlatDarculaLaf.setup();
        MainFrame frame = new MainFrame();

        MainFrameController mainController = new MainFrameController(frame);
        FormPanelController formPanelController = new FormPanelController(frame.getFormPanel());
        ImagenPanelController imagenPanelController = new ImagenPanelController(frame.getFormPanel().getImagenPanel());
        QueryPanelController queryPanelController = new QueryPanelController(frame.getQueryPanel());


        frame.addListeners(mainController);
        frame.getFormPanel().addActionListeners(formPanelController);
        frame.getFormPanel().getImagenPanel().addActionListeners(imagenPanelController);
        frame.getQueryPanel().addListeners(queryPanelController);


        frame.showWindow();
    }
}
