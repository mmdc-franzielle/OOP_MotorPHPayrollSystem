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
import model.Employee;
import model.roles.*;
import service.AuthService;
import service.EmployeeService;
import service.AttendanceService;
import service.PayrollService;
import ui.MainFrame;

/**
 * main controller: 
 * @author franzielle
 */

public class MainController {
    
    private final AuthService authService = new AuthService();
    private final EmployeeService employeeService = new EmployeeService();
    private final AttendanceService attendanceService = new AttendanceService();
    private final PayrollService payrollService = new PayrollService();
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
        // checks ID and last name
        User user = authService.authenticate(username, password, selectedRole);

        if (user != null) {
            // fetch specialized object
            Employee empProfile = employeeService.findEmployeeById(user.getEmployeeId());

            if (empProfile != null) {
                launchDashboard(user, empProfile);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Profile not found!");
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Invalid Employee ID or Last Name!");
        }
    }


    // RBAC: decide which dashboard to show based on the class of the object.
    // TO UPDATE: add more dashboards !!
    
    public void launchDashboard(User user, Employee emp) {
 
        System.out.println("Login Successful for: " + emp.getFirstName() + " (" + emp.getClass().getSimpleName() + ")");

        // RBAC check
        if (emp instanceof Admin || emp instanceof HR || emp instanceof Finance) {

            mainFrame.setMainContent(new AdminDashboard(this, user));
        } else {

            mainFrame.setMainContent(new EmployeeDashboard(this, user));
        }
    }

    public void handleLogout() {
        showLogin();
    }

    // accessors
    
    public AttendanceService getAttendanceService() {
        return attendanceService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public PayrollService getPayrollService() {
        return payrollService;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
