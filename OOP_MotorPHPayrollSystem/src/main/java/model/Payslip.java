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
    public double grossBasic, totalAllowance, sss, philhealth, pagibig, tax, netPay;
    
    // constructor
    public Payslip(double grossBasic, double totalAllowance, double sss, double philhealth, double pagibig, double tax, double netPay) {
        this.grossBasic = grossBasic;
        this.totalAllowance = totalAllowance;
        this.sss = sss;
        this.philhealth = philhealth;
        this.pagibig = pagibig;
        this.tax =  tax;
        this.netPay = netPay;
    }
    
    // getters (read value)
    
    public double getGrossABsic() {
        return grossBasic;
    } 
    
    public double getTotalAllowance() {
        return totalAllowance;
    }
    
    public double getSSS() {
        return sss;
    }
    
    public double getPhilhealth() {
        return philhealth;
    }
    
    public double getPagibig() {
        return pagibig;
    }
    
    public double getTax() {
        return tax;
    }
    
    public double getNetPay() {
        return netPay;
    }
 
    // setters (change value)
    
    public void setGrossBasic(double grossBasic) {
            this.grossBasic = grossBasic;
    }
    
    public void setTotalAllowance(double totalAllowance) {
        this.totalAllowance = totalAllowance;
    }
    
    public void setSSS(double sss) {
        this.sss = sss;
    }
    
    public void setPhilhealth(double philhealth) {
        this.philhealth = philhealth;
    }
    
    public void setPagibig(double pagibig) {
        this.pagibig = pagibig;
    }
    
    public void setTax(double tax) {
        this.tax = tax;
    }
    
    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
}
    
