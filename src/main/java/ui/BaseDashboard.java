/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

/**
 * base dashboard (blueprint) 
 * @author franzielle
 */

public abstract class BaseDashboard extends JPanel {
    
    protected static final int buttonWidth = 200;
    protected static final int buttonHeight = 45;
    protected static final int sidebarWidth = 250;
    protected static final int center = (sidebarWidth - buttonWidth) / 2;
    
    protected JPanel sidebar;
    protected JPanel contentArea;

    public BaseDashboard(String role) {
        
        // setup 
        setLayout(null);
        setBackground(Color.WHITE);
        
        this.setPreferredSize(new Dimension(1400, 850));

        // setup sidebar 
        sidebar = new JPanel();
        sidebar.setBounds(0, 0, 250, 850);
        sidebar.setBackground(new Color(0x333f4f));
        sidebar.setLayout(null);
        add(sidebar);

        // sidebar brand 
        JLabel textLogo = new JLabel("MotorPH Payroll System", SwingConstants.CENTER);
        textLogo.setForeground(Color.WHITE);
        textLogo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        textLogo.setBounds(0, 30, 250, 60);
        sidebar.add(textLogo);

        // setup content area
        contentArea = new JPanel();
        contentArea.setBounds(250, 0, 1150, 850);
        contentArea.setBackground(Color.WHITE);
        contentArea.setLayout(null);
        add(contentArea);
        
    }
    
    // methods !! ( okay lang ba 'to dito? )
    
    // switcher 
    public void switchContent(JPanel newPanel) {
        contentArea.removeAll();
        newPanel.setBounds(0, 0, contentArea.getWidth(), contentArea.getHeight());
        contentArea.add(newPanel);
        contentArea.revalidate();
        contentArea.repaint();
    }

    // buttons
    protected abstract void setupSidebarButtons();
}

