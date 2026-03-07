/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.deductions;

/**
 *
 * @author franzielle
 */

public class SSSDeduction extends DeductionService {
    
    @Override
    public double calculate(double grossBasic) {
        // SSS floor and ceiling
        if (grossBasic < 3250) return 135.00;
        if (grossBasic >= 24750) return 1125.00;

        // logic: the MSC is the starting point of the 500-peso bracket
        double salaryCredit = Math.floor((grossBasic - 3250) / 500) * 500 + 3250;
        
        // employee share is 4.5% of the MSC (middle value of the bracket)
        return round((salaryCredit + 250) * 0.045); 
    }
}

