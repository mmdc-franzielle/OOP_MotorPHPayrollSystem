/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.deductions;

/**
 *
 * @author franzielle
 */

public class PagIbigDeduction extends DeductionService {
    
    @Override
    public double calculate(double grossBasic) {
        // 1% for 1,500 and below, 2% for above 1,500
        double rate = (grossBasic > 1500) ? 0.02 : 0.01;
        
        // The maximum salary used for computation is capped at 5,000
        double cappedSalary = Math.min(grossBasic, 5000);
        
        return round(cappedSalary * rate);
    }
}
