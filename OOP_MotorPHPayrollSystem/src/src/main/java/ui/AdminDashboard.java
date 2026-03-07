/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import com.mycompany.oop_motorphpayrollsystem.MainController;
import javax.swing.*;
import java.awt.*;
import model.User;

/**
 * admin dashboard
 * @author franzielle
 */

public class AdminDashboard extends BaseDashboard {  
    
    private MainController controller;
      private User user;

    public AdminDashboard(MainController controller, User user) {
        super("Admin Portal");
        this.controller = controller;
        
        setupSidebarButtons();
    }

    @Override
    protected void setupSidebarButtons() {
        // 1. profile button
        JButton profileButton = new JButton("My Profile");
        profileButton.setBounds(center, 120, buttonWidth, buttonHeight);
        sidebar.add(profileButton);

        // 2. manage employees button
        JButton manageEmpButton = new JButton("Manage Employees");
        manageEmpButton.setBounds(center, 180, buttonWidth, buttonHeight);
        sidebar.add(manageEmpButton);
        
        manageEmpButton.addActionListener(e -> {
            switchContent(new ui.admin.ManageEmployeePanel(controller));
        });

        // 3. run payroll button
        JButton payrollButton = new JButton("Run Payroll");
        payrollButton.setBounds(center, 240, buttonWidth, buttonHeight);
        sidebar.add(payrollButton);
        
        payrollButton.addActionListener(e -> {
            switchContent(new ui.admin.PayrollPanel(controller));
        });

        // 4. leave approval button
        JButton leaveAppButton  = new JButton("Leave Approvals");
        leaveAppButton.setBounds(center, 300, buttonWidth, buttonHeight);
        sidebar.add(leaveAppButton );

        // 5. logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(center, 700, buttonWidth, buttonHeight);
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setForeground(Color.WHITE);
        
        logoutButton.addActionListener(e -> controller.handleLogout());
        
        sidebar.add(logoutButton);
    }

}