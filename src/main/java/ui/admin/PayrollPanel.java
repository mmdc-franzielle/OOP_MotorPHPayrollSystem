/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.admin;

import com.mycompany.oop_motorphpayrollsystem.MainController;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import model.Employee;
import model.Payslip;
import service.PayrollService;

/**
 * IMPORTANT NOTE: 
 *    net pay calculation needs to be reviewed kasi mali yung sweldo na binibigay (work hours not being read from .csv)
 * @author franzielle
 */

public class PayrollPanel extends JPanel {
    
    private MainController controller;
    private JTextField employeeIDField;

    private JComboBox<String> monthCombo;
    private JComboBox<Integer> yearCombo;
    private JTextArea summaryArea;

    public PayrollPanel(MainController controller) {
        this.controller = controller;

        // main layout
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // header
        JLabel title = new JLabel("Run Payroll");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        // input panel (left)
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // spacing

        // employee id label
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(new JLabel("Employee ID:"), gbc);

        // employee id field
        gbc.gridx = 0;
        gbc.gridy = 1;
        employeeIDField = new JTextField(15);
        leftPanel.add(employeeIDField, gbc);

        // period label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 5, 5, 5); // Extra gap above
        leftPanel.add(new JLabel("Select Period (MM/YYYY):"), gbc);

        // month and year dropdown
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        comboPanel.setOpaque(false);

        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        monthCombo = new JComboBox<>(months);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer[] years = new Integer[currentYear - 2020 + 1];
        for (int i = 0; i < years.length; i++) {
            years[i] = currentYear - i;
        }
        yearCombo = new JComboBox<>(years);

        comboPanel.add(monthCombo);
        comboPanel.add(new JLabel("  /  "));
        comboPanel.add(yearCombo);
        leftPanel.add(comboPanel, gbc);

        // calculate button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(25, 5, 5, 5);
        JButton calculateBtn = new JButton("Calculate Net Pay");
        calculateBtn.setBackground(new Color(0x333f4f));
        calculateBtn.setForeground(Color.WHITE);
        calculateBtn.setFocusPainted(false);
        calculateBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        leftPanel.add(calculateBtn, gbc);


        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(leftPanel, BorderLayout.NORTH);
        add(wrapper, BorderLayout.WEST);

        // summary display (right)
        summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        summaryArea.setBackground(new Color(0xF8F9FA));
        summaryArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(summaryArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Calculation Breakdown"));
        scrollPane.setPreferredSize(new Dimension(600, 500));
        add(scrollPane, BorderLayout.CENTER);

        calculateBtn.addActionListener(e -> runCalculation());
    }

    private void runCalculation() {
        String id = employeeIDField.getText().trim();

        String monthStr = monthCombo.getSelectedItem() + "/" + yearCombo.getSelectedItem();
        
        // get employee id
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID.");
            return;
        }

        Employee emp = controller.getEmployeeService().findEmployeeById(id);
        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Employee not found.");
            return;
        }
        
        // get hours worked/attendance
        double totalHours = controller.getAttendanceService().getTotalHoursForMonth(id, monthStr);
        
        // calculate
        PayrollService payrollService = new PayrollService();
        Payslip payslip = payrollService.calculateNetPay(emp, totalHours);

        displaySummary(emp, totalHours, payslip);
    }

    // paki-improve yung design ^^
    private void displaySummary(Employee emp, double hours, Payslip p) {
        StringBuilder sb = new StringBuilder();
        sb.append("==========================================\n");
        sb.append("          MOTORPH PAYROLL PREVIEW         \n");
        sb.append("==========================================\n");
        sb.append(String.format("Employee:    %s %s\n", emp.getFirstName(), emp.getLastName()));
        sb.append(String.format("Hours:       %.2f\n", hours));
        sb.append("------------------------------------------\n");
        sb.append(String.format("Gross Basic:           PHP %,.2f\n", p.grossBasic));
        sb.append(String.format("Total Allowances:      PHP %,.2f\n", p.totalAllowances));
        sb.append("------------------------------------------\n");
        sb.append(String.format("Total Deductions:    - PHP %,.2f\n", (p.sss + p.philhealth + p.pagibig + p.tax)));
        sb.append("------------------------------------------\n");
        sb.append(String.format("NET PAY:               PHP %,.2f\n", p.netPay));
        sb.append("==========================================\n");

        summaryArea.setText(sb.toString());
    }
}

    
