/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.User;

/**
 * 
 * @author franzielle
 */

public class AuthService {
    
private static final String EMPLOYEE_FILE = "MotorPH_EmployeeData.csv";

    public User authenticate(String username, String password, String selectedRole) {
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                // handle commas inside quotes
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length < 12) continue;

                String empID = data[0].replace("\"", "").trim();
                String lastName = data[1].replace("\"", "").trim();
                String position = data[11].replace("\"", "").trim();

                // check if ID matches username and last name matches password
                if (empID.equals(username) && lastName.equalsIgnoreCase(password)) {
                 
                    return new User(username, password, selectedRole, empID);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }
}