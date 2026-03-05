/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author franzielle
 */
public class Payslip {
    public double grossBasic, totalAllowances, sss, philhealth, pagibig, tax, netPay;

    public Payslip(double grossBasic, double totalAllowance, double sss, double philhealth, double pagibig, double tax, double netPay) {
        this.grossBasic = grossBasic;
        this.totalAllowances = totalAllowance;
        this.sss = sss;
        this.philhealth = philhealth;
        this.pagibig = pagibig;
        this.tax =  tax;
        this.netPay = netPay;
    }
}
    
