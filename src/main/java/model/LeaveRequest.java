/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author franzielle
 */

public class LeaveRequest {

    private String requestId;
    private String employeeId;
    private String startDate;
    private String endDate;
    private String type; // sick, vacation, emergency
    private String status; // pending, approved, rejected

    public LeaveRequest(String requestId, String employeeId, String startDate, String endDate, String type, String status) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
    }

    // getters
    public String getRequestId() { 
        return requestId; 
    }
    
    public String getEmployeeId() { 
        return employeeId; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    // setters
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    // for csv writing
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", requestId, employeeId, startDate, endDate, type, status);
    }
}
