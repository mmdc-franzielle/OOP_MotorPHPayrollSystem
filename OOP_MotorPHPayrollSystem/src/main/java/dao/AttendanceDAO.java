/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Attendance;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author franzielle
 */

public class AttendanceDAO {
    
    private final String filePath = "MotorPH_AttendanceRecord.csv";

    public List<Attendance> getAllAttendance() {
        List<Attendance> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
              
                if (values.length >= 6) {
                
                    records.add(new Attendance(
                            values[0], // employee id
                            values[1], // last name
                            values[2], // first name
                            values[3], // date
                            values[4], // login
                            values[5]  // logout
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading attendance: " + e.getMessage());
        }
        return records;
    }

    public List<Attendance> getAttendanceByEmployeeID(String employeeID) {
        List<Attendance> filtered = new ArrayList<>();
        for (Attendance record : getAllAttendance()) {
            if (record.getEmployeeID().equals(employeeID)) {
                filtered.add(record);
            }
        }
        return filtered;
    }
}
