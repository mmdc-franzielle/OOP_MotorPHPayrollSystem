/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AttendanceDAO;
import java.util.List;
import java.util.stream.Collectors;
import model.Attendance;

/**
 * attendance service: this service scans the attendanceRecord.csv to calculate work hours
 * @author franzielle
 */
 
public class AttendanceService {
    
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();

    // calculate total hours (month)
    public double getTotalHoursForMonth(String employeeId, String month) {
        List<Attendance> allRecords = attendanceDAO.getAllAttendance();
        double totalHours = 0;

        for (Attendance record : allRecords) {

            if (record.getEmployeeID().equals(employeeId) && record.getDate().startsWith(month)) {
                totalHours += record.calculateDayHours();
            }
        }
        return totalHours;
    }

    // calculate total hours (date)
    public double getTotalHoursForRange(String employeeId, String startDate, String endDate) {
        List<Attendance> allRecords = attendanceDAO.getAllAttendance();
        double totalHours = 0;

        for (Attendance record : allRecords) {
            if (record.getEmployeeID().equals(employeeId) && 
                isDateInRange(record.getDate(), startDate, endDate)) {
                totalHours += record.calculateDayHours();
            }
        }
        return totalHours;
    }
    
    // calculate late minutes
    public int getTotalLateMinutes(String employeeId, String month) {
        List<Attendance> allRecords = attendanceDAO.getAllAttendance();
        int totalLate = 0;

        for (Attendance record : allRecords) {
            if (record.getEmployeeID().equals(employeeId) && record.getDate().startsWith(month)) {
                totalLate += record.calculateLateMinutes(); 
            }
        }
        return totalLate;
    }

    // filters attendance
    public List<Attendance> getEmployeeAttendanceHistory(String employeeId) {
        return attendanceDAO.getAllAttendance().stream()
                .filter(record -> record.getEmployeeID().equals(employeeId))
                .collect(Collectors.toList());
    }

    // helper
    private boolean isDateInRange(String date, String start, String end) {
        return date.compareTo(start) >= 0 && date.compareTo(end) <= 0;
    }
}