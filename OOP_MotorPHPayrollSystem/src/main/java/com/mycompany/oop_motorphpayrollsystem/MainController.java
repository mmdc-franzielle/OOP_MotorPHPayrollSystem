/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oop_motorphpayrollsystem;

import ui.LoginPanel;
import ui.AdminDashboard;
import ui.EmployeeDashboard;
import javax.swing.JOptionPane;
import model.User;
import service.AuthService;
import ui.MainFrame;

/**
 * main controller: 
 * @author franzielle
 */

public class MainController {
    
    private final AuthService authService = new AuthService();
    private MainFrame mainFrame;

    public void start() {
        mainFrame = new MainFrame();
        showLogin();
        mainFrame.setVisible(true);
    }

    public void showLogin() {
        mainFrame.setMainContent(new LoginPanel(this));
    }

    public void handleLogin(String username, String password, String selectedRole) {
        User user = authService.authenticate(username, password, selectedRole);
        if (user != null) {
            launchDashboard(user);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Invalid Credentials!");
        }
    }

    private void launchDashboard(User user) {
        String role = user.getRole();
        
        if (role.equalsIgnoreCase("ADMIN") || 
            role.equalsIgnoreCase("HR") || 
            role.equalsIgnoreCase("FINANCE") ||
            role.equalsIgnoreCase("MANAGER")) { 
            
            mainFrame.setMainContent(new AdminDashboard(this, user));
            
        } else if (role.equalsIgnoreCase("IT")) {
            JOptionPane.showMessageDialog(mainFrame, "IT Dashboard is coming soon!");
            mainFrame.setMainContent(new EmployeeDashboard(this, user));
            
        } else {
            mainFrame.setMainContent(new EmployeeDashboard(this, user));
        }
    }

    public void handleLogout() {
        showLogin();
    }
}