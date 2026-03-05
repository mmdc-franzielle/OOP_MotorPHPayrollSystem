/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.LeaveRequest;
import java.io.*;
import java.util.*;

/**
 *
 * @author franzielle
 */

public class LeaveDAO {
    private final String FILE_PATH = "MotorPH_LeaveRequest.csv";

    // method
    public List<LeaveRequest> getAllRequests() {
        List<LeaveRequest> requests = new ArrayList<>();
        
        // check if file exists
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return requests; // return empty if none
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 6) {
                    requests.add(new LeaveRequest(data[0], data[1], data[2], data[3], data[4], data[5]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading leaves: " + e.getMessage());
        }
        return requests;
    }
    
    // handles writing back to csv
    public void saveRequests(List<LeaveRequest> requests) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            pw.println("RequestID,EmployeeID,StartDate,EndDate,Type,Status");
            for (LeaveRequest r : requests) {
                pw.println(r.toString());
            }
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }
}