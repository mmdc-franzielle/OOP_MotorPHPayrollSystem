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
            br.readLine(); // skip the header row 
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                Attendance record = new Attendance(
                    values[0], 
                    values[3], 
                    values[4], 
                    values[5]
                );
                records.add(record);
            }
        } catch (IOException e) {
            System.err.println("Error reading attendance file: " + e.getMessage());
        }
        return records;
    }
}
