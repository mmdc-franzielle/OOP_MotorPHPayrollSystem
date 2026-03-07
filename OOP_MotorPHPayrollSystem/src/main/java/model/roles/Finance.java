/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.roles;

import model.RegularEmployee;

/**
 *
 * @author franzielle
 */
public class Finance extends RegularEmployee {
    
    // constructor
    public Finance(String empNum, String firstName, String lastName, String birthDate,  String address, String phoneNumber,
            String sss, String philhealth, String tin, String pagibig,
            String status, String position, float basicPay, float riceSub,
            float phoneAl, float clothAl, float hourlyRate) {
        
        super(empNum, firstName, lastName, birthDate, address, phoneNumber, sss, philhealth, 
              tin, pagibig, status, position, basicPay, riceSub, 
              phoneAl, clothAl, hourlyRate);
    }
}
