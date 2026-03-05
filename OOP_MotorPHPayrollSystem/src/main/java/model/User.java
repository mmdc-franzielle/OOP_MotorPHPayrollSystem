/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author franzielle
 */

public class User {
    private String username;
    private String password;
    private String role;
    private String employeeID; 


    public User(String username, String password, String role, String employeeID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.employeeID = employeeID;
    }

    public String getEmployeeId() {
        return employeeID; 
    }

    public String getUsername() { 
        return username; 
    }
    
    public String getPassword() { 
        return password; 
    }
    
    public String getRole() { 
        return role; 
    }
}