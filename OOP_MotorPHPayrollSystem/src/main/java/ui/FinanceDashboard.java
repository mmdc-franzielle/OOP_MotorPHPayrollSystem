/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import com.mycompany.oop_motorphpayrollsystem.MainController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import model.Employee;
import model.User;

/**
 *
 * @author franzielle
 */

public class FinanceDashboard extends EmployeeDashboard {
    
    public FinanceDashboard(MainController controller, User user, Employee employee) {
        super(controller, user, employee);
    }
    
    @Override
    protected void setupSidebarButtons() {
        // load standard sidebard buttons
        super.setupSidebarButtons();

        // add special tools (header)
        JLabel adminLabel = new JLabel("IT TOOLS");
        adminLabel.setForeground(new Color(170, 170, 170));
        adminLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        adminLabel.setBounds(25, 380, 200, 20);
        sidebar.add(adminLabel);

        // add special tools (button)
        int y = 410;

        JButton payrollButton = createSidebarButton("Run Payroll",y);
        payrollButton.addActionListener(e -> switchContent(new ui.tools.PayrollPanel(controller)));
        sidebar.add(payrollButton);

        y += 60;
        JButton timesheetButton = createSidebarButton("Timesheet", y);
        timesheetButton.addActionListener(e -> switchContent(new ui.tools.TimesheetPanel()));
        sidebar.add(timesheetButton);
        
        y += 60;
        JButton statutoryRepButton = createSidebarButton("Statutory Report", y);
        sidebar.add(statutoryRepButton );
    }
}
