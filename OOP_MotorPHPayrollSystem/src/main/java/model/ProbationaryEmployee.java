/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import model.Employee;

/**
 *
 * @author franzielle
 */

public class ProbationaryEmployee extends Employee {

    public ProbationaryEmployee(String empNum, String firstName, String lastName, String birthDate, 
                               String sss, String philhealth, String tin, 
                               String pagibig, String status, String position, 
                               float basicPay, float riceSub, float phoneAl, 
                               float clothAl, float hourlyRate) {
        
        super(empNum, firstName, lastName, birthDate, sss, philhealth, 
              tin, pagibig, status, position, basicPay, riceSub, 
              phoneAl, clothAl, hourlyRate);
    }

    @Override
    public double calculateNetPay() {
        // Probationary employees might have different deduction logic
        return getBasicPay() + getRiceSub() + getPhoneAl() + getClothAl();
    }
}