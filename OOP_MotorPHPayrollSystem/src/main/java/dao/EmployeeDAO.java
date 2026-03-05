/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.*;
import java.util.*;
import model.*;

/**
 * employee dao: handles all .csv file operations for employees
 * @author franzielle
 */

public class EmployeeDAO {
    
   private final String CSV_FILE = "MotorPH_EmployeeData.csv";

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                // Regex to handle commas inside quotes
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (data.length < 19) continue;

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "").trim();
                }

                String employeeID = data[0];
                String lastName = data[1];
                String firstName = data[2];
                String birthDate = data[3];
                String sss = data[6];
                String philhealth = data[7];
                String tin = data[8];
                String pagibig = data[9];
                String status = data[10];
                String position = data[11];

                float basicPay = parseFormattedFloat(data[13]);
                float riceSub = parseFormattedFloat(data[14]);
                float phoneAl = parseFormattedFloat(data[15]);
                float clothAl = parseFormattedFloat(data[16]);
                float hourlyRate = parseFormattedFloat(data[18]);

               Employee employee;
                if ("Regular".equalsIgnoreCase(status)) {

                    employee = new RegularEmployee(employeeID, firstName, lastName, birthDate, sss, 
                                             philhealth, tin, pagibig, status, position, 
                                             basicPay, riceSub, phoneAl, clothAl, hourlyRate);
                } else {
                    employee = new ProbationaryEmployee(employeeID, firstName, lastName, birthDate, sss, 
                                                  philhealth, tin, pagibig, status, position, 
                                                  basicPay, riceSub, phoneAl, clothAl, hourlyRate);
                }
                employees.add(employee);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
        return employees;
    }

    private float parseFormattedFloat(String value) {
        try {
            return Float.parseFloat(value.replace(",", ""));
        } catch (Exception e) {
            return 0.0f;
        }
    }

    public void saveAllEmployees(List<Employee> employees) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            
            pw.println("Employee #, Last Name, First Name, Birthday, Address, Phone, "
                    + "SSS, Philhealth, TIN, Pag-ibig, Status, Position, Supervisor, Basic Salary,"
                    + "Rice Subsidy, Phone Allowance, Clothing Allowance, Gross Semi-monthly Rate, Hourly Rate");
            
            for (Employee e : employees) {
           
                pw.printf("%s,%s,%s,%s,,,%s,%s,%s,%s,%s,%s,,%.2f,%.2f,%.2f,%.2f,,%.2f%n",
                    e.getEmployeeID(), e.getLastName(), e.getFirstName(), e.getBirthDate(),
                    e.getSSS(), e.getPhilhealth(), e.getTin(), e.getPagibig(),
                    e.getStatus(), e.getPosition(), e.getBasicPay(), e.getRiceSub(),
                    e.getPhoneAl(), e.getClothAl(), e.getHourlyRate());
            }
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}