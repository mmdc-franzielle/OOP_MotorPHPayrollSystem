/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.deductions;

/**
 * government deductions (blueprint)
 * @author franzielle
 */

public abstract class DeductionService {

    // every deduction class must implement its own math calculation
    public abstract double calculate(double grossSalary);

    // common logic for all deductions: rounding to 2 decimal places
    protected double round(double value) {
        return Math.round(value * 100.0) / 100.0;
        
    }
}

