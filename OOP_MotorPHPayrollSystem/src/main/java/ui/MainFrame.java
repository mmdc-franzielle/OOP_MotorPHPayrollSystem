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
        
        setSize(1200, 700); 
    }

    public void setMainContent(JPanel newPanel) {
        getContentPane().removeAll();
        add(newPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
