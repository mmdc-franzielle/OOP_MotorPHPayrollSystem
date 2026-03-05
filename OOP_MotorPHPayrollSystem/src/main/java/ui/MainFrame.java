/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

/**
 *
 * main frame: the primary window for the application
 * uses a basic layout to allow the MainController to swap panels
 * @author franzielle
 */

public class MainFrame extends JFrame {
    
    public MainFrame() {
        setTitle("MotorPH Payroll System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        
        setSize(1400, 850); 
    }

    public void setMainContent(JPanel newPanel) {
        getContentPane().removeAll();
        add(newPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        setLocationRelativeTo(null);
        setVisible(true);
        
        JScrollPane pageScroll = new JScrollPane(newPanel);
        pageScroll.setBorder(null); 
        pageScroll.getVerticalScrollBar().setUnitIncrement(16); 

        add(pageScroll, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
