/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.deductions;

/**
 *
 * @author franzielle
 */

public class PhilHealthDeduction extends DeductionService {
    
    @Override
    public double calculate(double grossBasic) {
        // capped between 10,000 and 80,000
        double cappedSalary = Math.max(10000, Math.min(grossBasic, 80000));
        
        double totalPremium = cappedSalary * 0.045;
        return round(totalPremium / 2); // employee only pays 50%
    }
}

