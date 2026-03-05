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
    
    protected JPanel sidebar;
    protected JPanel contentArea;

    public BaseDashboard(String role) {
        
        // setup 
        setSize(1200, 700);
        setLayout(null);
        
        this.setPreferredSize(new Dimension(1000, 700));

        // setup sidebar 
        sidebar = new JPanel();
        sidebar.setBounds(0, 0, 250, 700);
        sidebar.setBackground(new Color(0x333f4f));
        sidebar.setLayout(null);
        add(sidebar);

        // sidebar brand
        JLabel logo = new JLabel("MotorPH Payroll", SwingConstants.CENTER);
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logo.setBounds(0, 30, 250, 40);
        sidebar.add(logo);

        // setup content area
        contentArea = new JPanel();
        contentArea.setBounds(250, 0, 950, 700);
        contentArea.setBackground(Color.WHITE);
        contentArea.setLayout(null);
        add(contentArea);
        
    }
    
    // methods !! ( okay lang ba 'to dito? )
    
    // switcher 
    public void setView(JPanel newView) {
        contentArea.removeAll();
        newView.setBounds(0, 0, 950, 700); 
        contentArea.add(newView);
        contentArea.revalidate();
        contentArea.repaint();
    }

    // buttons
    protected abstract void setupSidebarButtons();
}

