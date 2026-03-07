/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.*;
import java.util.*;
import model.*;
import model.roles.Admin;
import model.roles.Finance;
import model.roles.HR;
import model.roles.IT;

/**
 * 
 * @author franzielle
 */

public class EmployeeDAO {
    
    private final String CSV_FILE = "MotorPH_EmployeeData.csv";

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length < 19) {
                    continue;
                }

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "").trim();
                }

                String id = data[0];
                String lName = data[1];
                String fName = data[2];
                String bDay = data[3];
                String sss = data[6];
                String phil = data[7];
                String tin = data[8];
                String pagibig = data[9];
                String status = data[10];
                String position = data[11];
                float basic = parseFormattedFloat(data[13]);
                float rice = parseFormattedFloat(data[14]);
                float phone = parseFormattedFloat(data[15]);
                float cloth = parseFormattedFloat(data[16]);
                float hourly = parseFormattedFloat(data[18]);

                // RBAC 
                Employee employee;
                String pos = position.toLowerCase();

                if (pos.contains("hr") || pos.contains("human resources")) {
                    employee = new HR(id, fName, lName, bDay, sss, phil, tin, pagibig, status, position, basic, rice, phone, cloth, hourly);
                } else if (pos.contains("account") || pos.contains("finance")) {
                    employee = new Finance(id, fName, lName, bDay, sss, phil, tin, pagibig, status, position, basic, rice, phone, cloth, hourly);
                } else if (pos.contains("it") || pos.contains("network")) {
                    employee = new IT(id, fName, lName, bDay, sss, phil, tin, pagibig, status, position, basic, rice, phone, cloth, hourly);
                } else if (pos.contains("admin") || pos.contains("chief") || pos.contains("ceo")) {
                    employee = new Admin(id, fName, lName, bDay, sss, phil, tin, pagibig, status, position, basic, rice, phone, cloth, hourly);
                } else {
                    // status
                    if ("Regular".equalsIgnoreCase(status)) {
                        employee = new RegularEmployee(id, fName, lName, bDay, sss, phil, tin, pagibig, status, position, basic, rice, phone, cloth, hourly);
                    } else {
                        employee = new ProbationaryEmployee(id, fName, lName, bDay, sss, phil, tin, pagibig, status, position, basic, rice, phone, cloth, hourly);
                    }
                }
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

   public void saveAllEmployees(List<Employee> employees) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            // Write the header exactly as MotorPH expects it
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
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
        
   }

    private float parseFormattedFloat(String value) {
        try {
            return Float.parseFloat(value.replace(",", ""));
        } catch (Exception e) {
            return 0.0f;
        }
    }
}
