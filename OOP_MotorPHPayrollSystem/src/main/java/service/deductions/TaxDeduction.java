/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.deductions;

/**
 *
 * @author franzielle
 */

public class TaxDeduction extends DeductionService {

    public TaxDeduction() {
    }
    
    @Override
    public double calculate(double taxableIncome) {
        
        // bracket 1: 20,833 and below
        if (taxableIncome <= 20833.33) {
            return 0.0;
        }
        
        // bracket 2: 20,833.34 to 33,333
        if (taxableIncome <= 33333) {
            return round((taxableIncome - 20833.33) * 0.15);
        }
        
        // bracket 3: 33,333 to 66,667
        if (taxableIncome <= 66667) {
            return round(1875 + (taxableIncome - 33333.33) * 0.20);
        }
        
        // bracket 4: 66,667 to 166,667
        if (taxableIncome <= 166667) {
            return round(8541.67 + (taxableIncome - 66666.67) * 0.25);
        }
        
        // bracket 5: 166,667 to 666,667
        if (taxableIncome <= 666667) {
            return round(33541.67 + (taxableIncome - 166666.67) * 0.30);
        }
        
        // bracket 6: 666,667 and above
        return round(183541.67 + (taxableIncome - 666666.67) * 0.35);
    }
}
