/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * 
 * @author franzielle
 */

public abstract class Employee {
    
        private String employeeID;
        private String firstName;
        private String lastName;
        private String birthDate;
        private String sss;
        public String philhealth;
        private String tin;
        private String pagibig;
        private String status;
        private String position;
        private float basicPay;
        private float riceSub;
        private float phoneAl;
        private float clothAl;
        private float hourlyRate;

    // constructor
     public Employee(String employeeID, String firstName, String lastName, String birthDate, 
                    String sss, String philhealth, String tin, String pagibig, 
                    String status, String position, float basicPay, float riceSub, 
                    float phoneAl, float clothAl, float hourlyRate) {
         
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sss = sss;
        this.philhealth = philhealth;
        this.tin = tin;
        this.pagibig= pagibig;
        this.status = status;
        this.position = position;
        this.basicPay = basicPay;
        this.riceSub = riceSub;
        this.phoneAl = phoneAl;
        this.clothAl = clothAl;
        this.hourlyRate = hourlyRate;
    }

    // abstract method: every subclass (admin, hr, it) must implement their own payroll logic
    public abstract double calculateNetPay();

    // getters
    public String getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getSSS() {
        return sss;
    }

    public String getPhilhealth() {
        return philhealth;
    }

    public String getTin() {
        return tin;
    }

    public String getPagibig() {
        return pagibig;
    }

    public String getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public float  getBasicPay() {
        return basicPay;
    }

    public float  getRiceSub() {
        return riceSub;
    }

    public float getPhoneAl() {
        return phoneAl;
    }

    public float  getClothAl() {
        return clothAl;
    }

    public float getHourlyRate() {
        return hourlyRate;
       
    }
    
    // setters

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setSSS(String sss) {
        this.sss = sss;
    }

    public void setPhilhealth(String philhealth) {
        this.philhealth = philhealth;
    }

    public void setTin(String Tin) {
        this.tin = tin;
    }

    public void setPagibig(String pagibig) {
        this.pagibig = pagibig;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBasicPay(float basicPay) {
        this.basicPay = basicPay;
    }

    public void setRiceSub(float riceSub) {
        this.riceSub = riceSub;
    }

    public void setPhoneAl(float  phoneAl) {
        this.phoneAl = phoneAl;
    }

    public void setClothAl(float clothAl) {
        this.clothAl = clothAl;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Role getRole() {
        if (position.equalsIgnoreCase("HR Manager")) {
            return Role.HR;
        } else if (position.equalsIgnoreCase("Account Manager")) {
            return Role.FINANCE;
        } else if (position.equalsIgnoreCase("IT Specialist")) {
            return Role.IT;
        } else if (position.equalsIgnoreCase("Chief Executive Officer")
                || position.equalsIgnoreCase("Chief Operating Officer")) {
            return Role.ADMIN;
        } else {
            return Role.EMPLOYEE;
        }
    }
}
    