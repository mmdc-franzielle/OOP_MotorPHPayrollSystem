/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import com.mycompany.oop_motorphpayrollsystem.MainController;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author franzielle
 */

public class LoginPanel extends JPanel {
    
    private MainController controller;
    
    public LoginPanel(MainController controller) {
        this.controller = controller;
         
        this.setLayout(new GridBagLayout()); 
        this.setBackground(new Color(0x333f4f));
        
        // grid bag layout
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0x333f4f)); 

        // login card
        JPanel loginCard = new JPanel();
        loginCard.setPreferredSize(new Dimension(500, 500));
        loginCard.setLayout(null); 
        loginCard.setBackground(Color.WHITE);
        loginCard.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

        // header 
        JLabel greeting = new JLabel("Welcome to", SwingConstants.CENTER);
        greeting.setFont(new Font("Segoe UI", Font.BOLD, 15));
        greeting.setBounds(0, 45, 500, 45);
        loginCard.add(greeting);
                
        JLabel title = new JLabel("MotorPH Payroll System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 25));
        title.setBounds(0, 75, 500, 45);
        loginCard.add(title);
        
        // role selction
        JLabel roleLabel = new JLabel("Select Role");
        roleLabel.setBounds(100, 135, 300, 20);
        loginCard.add(roleLabel);

        // role dropdown (JComboBox)
        String[] roles = {"Employee", "Admin", "HR", "Finance", "IT"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setBounds(100, 160, 300, 35);
        roleBox.setBackground(Color.WHITE);
        roleBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginCard.add(roleBox);

        // username label & field
        JLabel userLabel = new JLabel("Username (Employee ID)");
        userLabel.setBounds(100, 210, 300, 20);
        loginCard.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(100, 235, 300, 40);
        userField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        loginCard.add(userField);

        // password label and field
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(100, 290, 300, 20);
        loginCard.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(100, 315, 300, 40);
        passField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 210)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        loginCard.add(passField);

        // login button 
       JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 380, 300, 45);
        loginButton.setBackground(new Color(0x333f4f));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginCard.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            String selectedRole = (String) roleBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            // call from the controller
            controller.handleLogin(username, password, selectedRole);
        });
        
        add(loginCard);
    }
}