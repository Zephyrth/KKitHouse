package org.dam.Controllers;

import org.dam.Views.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController implements ActionListener {
    public static final String INICIO = "INICIO", NAVIGATE_INICIO = "NAVIGATE_INICIO";
    private MainFrame mainFrame;

    public MainFrameController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void handleNavigateTo(String panel) {
        this.mainFrame.navigate(panel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case NAVIGATE_INICIO: {
                handleNavigateTo(INICIO);
                break;
            }
        }
    }
}
