/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import model.User;

/**
 * purpose: it reads MotorPH_LoginCredentials.csv to verify if a user is allowed in.
 * @author franzielle
 */

public class AuthService {
    
private static final String LOGIN_FILE = "MotorPH_LoginCredentials.csv";

    public User authenticate(String username, String password, String selectedRole) {
        File file = new File(LOGIN_FILE);
        
        if (!file.exists()) {
            System.err.println("FILE NOT FOUND: " + file.getAbsolutePath());
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 3) continue;

                String fileUser = data[0].replace("\"", "").trim();
                String filePass = data[1].replace("\"", "").trim();
                String fileRole = data[2].replace("\"", "").trim();

                if (fileUser.equals(username) && filePass.equals(password)) {
                    // FIX: Pass fileUser as the employeeId (4th argument)
                    return new User(fileUser, filePass, fileRole, fileUser);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }
}