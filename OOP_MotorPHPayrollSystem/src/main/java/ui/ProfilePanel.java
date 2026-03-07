/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import model.Employee;

/**
 * profile panel
 * @author franzielle
 */

public class ProfilePanel extends JPanel {
    
    public ProfilePanel(Employee emp) {
        setLayout(null);
        setBackground(Color.WHITE);
        // standard size
        setBounds(0, 0, 1150, 850);

        // header
        JLabel titleLabel = new JLabel("My Profile");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBounds(30, 20, 200, 30); // Title stays at the top
        add(titleLabel);

        int y = 70;
        int spacing = 35;
        
        // section 1: personal info
        addSectionHeader("Personal Information", y);
        y += 40; 

        addInfoRow("Employee ID:", emp.getEmployeeID(), y);
        addInfoRow("Full Name:", emp.getFirstName() + " " + emp.getLastName(), y += spacing);
        addInfoRow("Birthdate:", emp.getBirthDate(), y += spacing);
        addInfoRow("Address:", emp.getAddress(), y += spacing);
        addInfoRow("Phone Number:", emp.getPhoneNumber(), y += spacing);

        // section 2: gov't details
        y += 30;
        JSeparator line = new JSeparator();
        line.setBounds(30, y, 500, 2);
        add(line);

        y += 20; 
        addInfoRow("SSS Number:", emp.getSSS(), y);
        addInfoRow("PhilHealth:", emp.getPhilhealth(), y += spacing);
        addInfoRow("TIN:", emp.getTin(), y += spacing);
        addInfoRow("Pag-IBIG:", emp.getPagibig(), y += spacing);

        // section 3: employment info
        y += 60; 
        addSectionHeader("Employment Details", y);

        y += 40; // Move down for the content
        addInfoRow("Position:", emp.getPosition(), y);
        addInfoRow("Status:", emp.getStatus(), y += spacing);
        addInfoRow("Hourly Rate:", String.format("PHP %,.2f", 
                emp.getHourlyRate()), y += spacing);
    }
    
    // helper to create section headers with a separator line
    
    private void addSectionHeader(String text, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(new Color(0x333f4f));
        label.setBounds(50, y, 400, 30);
        add(label);

        JSeparator sep = new JSeparator();
        sep.setBounds(50, y + 30, 600, 10);
        add(sep);
    }

    // helper to create a label and its corresponding value

    private void addInfoRow(String labelText, String valueText, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(Color.GRAY);
        label.setBounds(70, y, 150, 25);
        add(label);

        JTextField value = new JTextField(valueText != null && !valueText.trim().isEmpty() ? valueText : "N/A");
        value.setEditable(false);
        value.setBackground(Color.WHITE);
        value.setBorder(null);
        value.setFont(new Font("Segoe UI", Font.BOLD, 14));
        value.setBounds(230, y, 600, 25);
        add(value);
    }
}
