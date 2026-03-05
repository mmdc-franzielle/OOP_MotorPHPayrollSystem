/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Employee;
import model.Payslip;
import service.deductions.*;

/**
 *
 * @author franzielle
 */

public class PayrollService {

    private SSSDeduction sssCalculation = new SSSDeduction();
    private PhilHealthDeduction phCalculation = new PhilHealthDeduction();
    private PagIbigDeduction piCalculation = new PagIbigDeduction();
    private TaxDeduction taxCalculation = new TaxDeduction();

    public Payslip calculateNetPay(Employee emp, double hoursWorked) {
        // 1. calculate gross
        double hourlyRate = emp.getHourlyRate();
        double grossBasic = hoursWorked * hourlyRate;
        
        // 2. allowances
        double totalAllowances = emp.getRiceSub() + emp.getPhoneAl() + emp.getClothAl();

        // 3. deductions
        double sss = sssCalculation.calculate(grossBasic);
        double philhealth = phCalculation.calculate(grossBasic);
        double pagibig = piCalculation.calculate(grossBasic);

        // 4. calculate taxable income
        double taxableIncome = grossBasic - (sss + philhealth + pagibig);
        
        // 5. withholding tax
        double tax = taxCalculation.calculate(taxableIncome);

        // 6. final net pay
        double netPay = (taxableIncome - tax) + totalAllowances;
        
        // return the full breakdown in a payslip 
       
        return new Payslip (
            grossBasic, 
            totalAllowances, 
            sss, 
            philhealth, 
            pagibig, 
            tax, 
            netPay
        );
    }
}