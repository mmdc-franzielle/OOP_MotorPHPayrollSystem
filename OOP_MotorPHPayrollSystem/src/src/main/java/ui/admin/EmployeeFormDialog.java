/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ui.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Employee;
import model.ProbationaryEmployee;
import model.RegularEmployee;


/** 
 * employee form: a popup window to get employee details
 * @author franzielle
 */

public class EmployeeFormDialog extends JDialog {
    private JTextField[] fields;
    private JComboBox<String> statusCombo;
    private boolean confirmed = false;
    private Employee resultEmployee = null;

    // label names corresponding to the csv/model structure
    private final String[] labels = {
        "Employee #", "Last Name", "First Name", "Birthday", "SSS #", 
        "Philhealth #", "TIN #", "Pag-ibig #", "Position", "Basic Salary", 
        "Rice Subsidy", "Phone Allowance", "Clothing Allowance", "Hourly Rate"
    };

    public EmployeeFormDialog(Frame parent, Employee existingEmp) {
        super(parent, (existingEmp == null ? "Add New Employee" : "Edit Employee"), true);
        setLayout(new BorderLayout(15, 15));

        JPanel formPanel = new JPanel(new GridLayout(labels.length + 1, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            formPanel.add(new JLabel(labels[i]));
            fields[i] = new JTextField(20);
            formPanel.add(fields[i]);
        }

        // dropdown for status
        formPanel.add(new JLabel("Employee Status:"));
        statusCombo = new JComboBox<>(new String[]{"Regular", "Probationary"});
        formPanel.add(statusCombo);

        if (existingEmp != null) {
            fillExistingData(existingEmp);
            fields[0].setEditable(false); // id should not be changed during update
        }

        // buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save Data");
        JButton cancelBtn = new JButton("Cancel");

        saveBtn.addActionListener(e -> handleSave());
        cancelBtn.addActionListener(e -> dispose());

        btnPanel.add(cancelBtn);
        btnPanel.add(saveBtn);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    private void handleSave() {
        try {
            String status = (String) statusCombo.getSelectedItem();
            
            if ("Regular".equalsIgnoreCase(status)) {
                resultEmployee = new RegularEmployee(
                    fields[0].getText(), fields[2].getText(), fields[1].getText(), fields[3].getText(),
                    fields[4].getText(), fields[5].getText(), fields[6].getText(), fields[7].getText(),
                    status, fields[8].getText(),
                    getFloat(9), getFloat(10), getFloat(11), getFloat(12), getFloat(13)
                );
            } else {
                resultEmployee = new ProbationaryEmployee(
                    fields[0].getText(), fields[2].getText(), fields[1].getText(), fields[3].getText(),
                    fields[4].getText(), fields[5].getText(), fields[6].getText(), fields[7].getText(),
                    status, fields[8].getText(),
                    getFloat(9), getFloat(10), getFloat(11), getFloat(12), getFloat(13)
                );
            }
            confirmed = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Salary, Allowances, and Hourly Rate must be numeric.", 
                                          "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private float getFloat(int index) {
        String val = fields[index].getText().trim().replace(",", "");
        return val.isEmpty() ? 0.0f : Float.parseFloat(val);
    }

    private void fillExistingData(Employee emp) {
        // Matches the labels array indices
        fields[0].setText(emp.getEmployeeID());
        fields[1].setText(emp.getLastName());
        fields[2].setText(emp.getFirstName());
        fields[3].setText(emp.getBirthDate());
        fields[4].setText(emp.getSSS());
        fields[5].setText(emp.getPhilhealth());
        fields[6].setText(emp.getTin());
        fields[7].setText(emp.getPagibig());
        fields[8].setText(emp.getPosition());
        fields[9].setText(String.valueOf(emp.getBasicPay()));
        fields[10].setText(String.valueOf(emp.getRiceSub()));
        fields[11].setText(String.valueOf(emp.getPhoneAl()));
        fields[12].setText(String.valueOf(emp.getClothAl()));
        fields[13].setText(String.valueOf(emp.getHourlyRate()));
        statusCombo.setSelectedItem(emp.getStatus());
    }

    public Employee getEmployeeFromForm() {
        return confirmed ? resultEmployee : null;
    }
}