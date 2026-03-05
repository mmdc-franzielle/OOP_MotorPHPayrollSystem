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

    // methods coming soon (WIP)
    /*
    public void runPayroll(int month) {
        // TODO: Logic for calculating monthly payroll for all employees
        // Will involve PayrollService and AttendanceService
    }

    public void generatePayslip(String employeeId) {
        // TODO: Logic to generate PDF or View for individual payslip
    }

    public void submitAttendance(String employeeId, String date, String timeIn, String timeOut) {
        // TODO: Logic to record daily time logs to CSV via AttendanceService
    }
    
    public void approveLeaveRequest(String requestId) {
        // TODO: Logic for HR/Managers to approve leave applications
    }
    */
}