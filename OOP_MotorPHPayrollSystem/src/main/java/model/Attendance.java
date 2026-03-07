/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.Duration;
import java.time.LocalTime;

/**
 * attendance model: represents attendance for an employee
 * @author franzielle
 */

public class Attendance {
    
    private String employeeID;
    private String lastName;
    private String firstName;
    private String date;
    private String timeIn;
    private String timeOut;

    public Attendance(String employeeID, String lastName, String firstName,
            String date, String timeIn, String timeOut) {
        this.employeeID = employeeID;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    // logic to calculate hours for this specific day
    public double calculateDayHours() {
        try {
            // padding for times like "8:00" -> "08:00"
            String start = (timeIn.length() == 4) ? "0" + timeIn : timeIn;
            String end = (timeOut.length() == 4) ? "0" + timeOut : timeOut;

            LocalTime in = LocalTime.parse(start);
            LocalTime out = LocalTime.parse(end);

            Duration duration = Duration.between(in, out);
            double hours = duration.toMinutes() / 60.0;

            // deduct 1 hour for lunch if worked more than 4 hours
            return (hours > 4) ? hours - 1.0 : hours;
        } catch (Exception e) {
            return 0.0;
        }
    }

    // logic for late minutes
    public double calculateLateMinutes() {
        try { 
            String start = (this.timeIn.length() == 4) ? "0" + this.timeIn : this.timeIn;
            
            LocalTime shiftStart = LocalTime.of(8, 0); 
            LocalTime actualIn = LocalTime.parse(start);

            // 10-min grace period (8:10 is not late, 8:11 is)
            if (actualIn.isBefore(LocalTime.of(8, 11))) {
                return 0;
            }
            
            return (double) Duration.between(shiftStart, actualIn).toMinutes(); 
            
        } catch (Exception e) {
            return 0.0; 
        }
    }

    // getters
    public String getEmployeeID() { 
        return employeeID; 
    }
    
    public String getLastName() {
        return lastName;
    }
    
     public String getFirstName() { 
        return firstName; 
    }
    
    public String getDate() { 
        return date; 
    }
    
    public String getTimeIn() { 
        return timeIn; 
    }
    
    public String getTimeOut() { 
        return timeOut; 
    }
}