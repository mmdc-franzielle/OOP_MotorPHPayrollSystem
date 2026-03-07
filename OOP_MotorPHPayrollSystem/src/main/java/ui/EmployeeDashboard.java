/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import com.mycompany.oop_motorphpayrollsystem.MainController;
import javax.swing.*;
import java.awt.*;
import model.Employee;
import model.User;

/**
 * employee dashboard
 * @author franzielle
 */

public class EmployeeDashboard extends BaseDashboard {
    
    protected MainController controller;
    protected User user;
    protected Employee employee;
    
    
    // constructor
    public EmployeeDashboard(MainController controller, User user, Employee employee) {
        super("Employee Portal"); 
        this.controller = controller;
        this.employee = employee;
        setupSidebarButtons();
    }

    @Override
    protected void setupSidebarButtons(){
        
        // standard sidebar buttons (y-position)
        int yPosition = 120;
        
        // my profile
        JButton profileButton = createSidebarButton("My Profile", yPosition);
        profileButton.addActionListener(e -> switchContent(new ui.ProfilePanel(this.employee)));
        sidebar.add(profileButton);

        // attendance
        yPosition += 60;
        JButton attendanceButton = createSidebarButton("View Attendance", yPosition);
        // attendanceButton.addActionListener(e -> switchContent(new ui.AttendancePanel(employee)));
        sidebar.add(attendanceButton);

        // payslip
        yPosition += 60;
        JButton payslipButton = createSidebarButton("Payslip", yPosition);
        // payslipButton.addActionListener(e -> switchContent(new ui.PayslipPanel(employee)));
        sidebar.add(payslipButton);

        // leave request
        yPosition += 60;
        JButton leaveReqButton = createSidebarButton("Leave Requests", yPosition);
        // leaveReqButton.addActionListener(e -> switchContent(new ui.LeaveRequestPanel(employee)));
        sidebar.add(leaveReqButton);

        // logout
        setupLogoutButton();
    }
    
    protected JButton createSidebarButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(25, yPosition, 200, 40);
        button.setFocusPainted(false);
        return button;
    }
    
    protected void setupLogoutButton() {
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(25, 750, 200, 40);
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> controller.handleLogout());
        sidebar.add(logoutButton);
    }
}
        
