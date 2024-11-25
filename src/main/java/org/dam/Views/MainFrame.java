package org.dam.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.dam.Controllers.MainFrameController.*;

public class MainFrame extends JFrame {
    private JPanel mainFrame;
    private JButton btn_inicio;
    private JButton btn_create;
    private JButton btn_table;
    private JPanel containerButton;
    private JPanel containerPanels;
    private CardLayout navegador;
    private InitPanel initPanel;
    private FormPanel formPanel;
    private QueryPanel queryPanel;

    public MainFrame() {
        initWindow();
        addPanels();
        setCommands();
    }

    public QueryPanel getQueryPanel() {
        return queryPanel;
    }

    public void setQueryPanel(QueryPanel queryPanel) {
        this.queryPanel = queryPanel;
    }

    public void setFormPanel(FormPanel formPanel) {
        this.formPanel = formPanel;
    }

    private void addPanels() {
        //Obtener layout para navegar
        navegador = (CardLayout) containerPanels.getLayout();
        //Instanciar Paneles
        initPanel = new InitPanel();
        initPanel.setBackground("/KKITHOUSE.png");

        formPanel = new FormPanel();
        formPanel.setBackground("/backgroundForm.png");

        queryPanel = new QueryPanel(formPanel);




        containerPanels.add(initPanel, INICIO);
        containerPanels.add(formPanel, CREATE);
        containerPanels.add(queryPanel, QUERY);
    }

    public void initWindow() {
        setContentPane(mainFrame);
        pack();
        
        setLocationRelativeTo(null);
        setCommands();
        initComponents();
    }


    public void showWindow() {
        setVisible(true);
    }


    public void closeWindow() {
        dispose();
    }


    public void setCommands() {

    }


    public void addListeners(ActionListener listener) {
        btn_inicio.addActionListener(listener);
        btn_create.addActionListener(listener);
        btn_table.addActionListener(listener);

    }

    public FormPanel getFormPanel() {
        return formPanel;
    }

    public void initComponents() {
        btn_inicio.setActionCommand(NAVIGATE_INICIO);
        btn_create.setActionCommand(NAVIGATE_CREATE);
        btn_table.setActionCommand(NAVIGATE_QUERY);

    }


    public void navigate(String panel) {
        navegador.show(containerPanels, panel);
    }
}
