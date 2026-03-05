/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/* package ui;

import com.mycompany.oop_motorphpayrollsystem.MainController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.*;
import service.EmployeeService;

/** WORK IN PROGRESS
 *
 * @author franzielle
 */
/*
public class ManageEmployeePanel extends JPanel {
private MainController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public ManageEmployeePanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        
        setupTable();
        setupButtons();
        refreshTable();
    }

    private void setupTable() {
        String[] columns = {"ID", "Last Name", "First Name", "Position", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void setupButtons() {
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("Add New");
        
        // FIX: Passes the MainFrame window as the parent for the pop-up
        addBtn.addActionListener(e -> {
            EmployeeFormDialog dialog = new EmployeeFormDialog(controller.getMainFrame(), null);
            dialog.setVisible(true);
            
            Employee result = dialog.getEmployeeFromForm();
            if (result != null) {
                controller.getEmployeeService().addEmployee(result);
                refreshTable();
            }
        });

        btnPanel.add(addBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Employee> list = controller.getEmployeeService().getAllEmployees();
        for (Employee e : list) {
            tableModel.addRow(new Object[]{e.getEmpNum(), e.getLastName(), e.getFirstName(), e.getPosition(), e.getStatus()});
        }
    }
}*/
