/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.DataService;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

/**
 * csv handler: central engine for file operations using OpenCSV.
 * @author franzielle
 */

public class CSVHandler implements DataService {
    
    private static final Map<String, String> filePaths = new HashMap<>();

    static {
        filePaths.put("employee", "MotorPH_EmployeeData.csv");
        filePaths.put("attendance", "MotorPH_AttendanceRecord.csv");
        filePaths.put("leave", "MotorPH_LeaveRequest.csv");
        filePaths.put("useraccount", "MotorPH_LoginCredentials.csv");
    }

    @Override
    public List<String[]> readData(String key) {
        String filePath = filePaths.get(key);
        List<String[]> records = new ArrayList<>();
        if (filePath == null) {
            return records;
        }

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withSkipLines(1)
                .build()) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                records.add(nextLine);
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading data: " + e.getMessage());
        }
        return records;
    }


    @Override
    public List<String[]> readDatawithHeader(String key) {
        String filePath = filePaths.get(key);
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                records.add(nextLine);
            }
        } catch (Exception e) {
}
        return records;
    }

    @Override
    public void writeData(String key, List<String[]> data) {
        String filePath = filePaths.get(key);
        if (filePath == null) return;

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(data);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath + " | " + e.getMessage());
        }
    }

    @Override
    public void appendData(String key, String[] newData) {
        String filePath = filePaths.get(key);
        if (filePath == null) return;

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
            writer.writeNext(newData);
        } catch (IOException e) {
            System.err.println("Error appending to file: " + filePath + " | " + e.getMessage());
        }
    }

    @Override
    public void deleteData(String key, String id) {
        List<String[]> allData = readDatawithHeader(key);
        if (allData.isEmpty()) return;

        // Keep the header, but remove the row matching the ID
        String[] header = allData.get(0);
        List<String[]> dataRows = new ArrayList<>(allData.subList(1, allData.size()));
        
        boolean removed = dataRows.removeIf(row -> row.length > 0 && row[0].equals(id));

        if (removed) {
            List<String[]> updatedList = new ArrayList<>();
            updatedList.add(header);
            updatedList.addAll(dataRows);
            writeData(key, updatedList);
        }
    }

    @Override
    public boolean updateData(String key, String id, String[] updatedRow) {
        List<String[]> allData = readDatawithHeader(key);
        if (allData.isEmpty()) return false;

        boolean found = false;
        
        for (int i = 1; i < allData.size(); i++) {
            if (allData.get(i).length > 0 && allData.get(i)[0].equals(id)) {
                allData.set(i, updatedRow);
                found = true;
                break;
            }
        }

        if (found) {
            writeData(key, allData);
        }
        return found;
    }
}
