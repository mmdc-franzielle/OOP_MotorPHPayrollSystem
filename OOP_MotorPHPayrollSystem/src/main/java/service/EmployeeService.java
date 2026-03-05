/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.EmployeeDAO;
import model.Employee;
import dao.EmployeeDAO;
import java.util.List;

/**
 * employee service: 
 * @author franzielle
 */

public class EmployeeService {
 
 private EmployeeDAO employeeDAO = new EmployeeDAO();
 
    public Employee findEmployeeById(String id) {
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        for (Employee emp : allEmployees) {
            if (emp.getEmployeeID().equals(id)) {
                return emp;
            }
        }
        return null; 
    }
    
    // create
    public void addEmployee(Employee emp) {
        List<Employee> list = employeeDAO.getAllEmployees();
        list.add(emp);
        employeeDAO.saveAllEmployees(list);
    }

    // update
    public boolean updateEmployee(Employee updatedEmp) {
        List<Employee> list = employeeDAO.getAllEmployees();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmployeeID().equals(updatedEmp.getEmployeeID())) {
                list.set(i, updatedEmp); 
                employeeDAO.saveAllEmployees(list);
                return true;
            }
        }
        return false;
    }

    // delete
    public boolean deleteEmployee(String id) {
        List<Employee> list = employeeDAO.getAllEmployees();
        boolean removed = list.removeIf(emp -> emp.getEmployeeID().equals(id));
        
        if (removed) {
            employeeDAO.saveAllEmployees(list);
        }
        return removed;
    }
}