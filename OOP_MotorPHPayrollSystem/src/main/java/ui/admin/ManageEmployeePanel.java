/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.admin;

import com.mycompany.oop_motorphpayrollsystem.MainController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.*;

/** 
 * manage employee panel: displays the employee list and provides CRUD controls.
 * @author franzielle
 */

public class ManageEmployeePanel extends JPanel {
    private MainController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public ManageEmployeePanel(MainController controller) {
        this.controller = controller;

        // main layout & padding
        setLayout(new BorderLayout(0, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 40));
        
        // header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xEEEEEE)));

        // header - title
        JLabel pageTitle = new JLabel("Manage Employees");
        pageTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        pageTitle.setForeground(new Color(0x333f4f));
        headerPanel.add(pageTitle, BorderLayout.WEST);
        
        // header - buttons
        headerPanel.add(createTopButtons(), BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);;

        setupTable();
        
        JPanel bottomSpacer = new JPanel();
        bottomSpacer.setPreferredSize(new Dimension(0, 10));
        bottomSpacer.setOpaque(false);
        add(bottomSpacer, BorderLayout.SOUTH);
        
        refreshTable();
        
    }
    
    private JPanel createTopButtons() {

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        // standard button size
        Dimension buttonSize = new Dimension(100, 32);
        addButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);

        // ADD (action)
        addButton.addActionListener(e -> {
            EmployeeFormDialog dialog = new EmployeeFormDialog(controller.getMainFrame(), null);
            dialog.setVisible(true);
            Employee result = dialog.getEmployeeFromForm();
            if (result != null) {
                controller.getEmployeeService().addEmployee(result);
                refreshTable();
            }
        });

        // UPDATE (action)
        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String id = table.getValueAt(selectedRow, 0).toString();
                Employee emp = controller.getEmployeeService().findEmployeeById(id);
                EmployeeFormDialog dialog = new EmployeeFormDialog(controller.getMainFrame(), emp);
                dialog.setVisible(true);
                if (dialog.getEmployeeFromForm() != null) {
                    controller.getEmployeeService().updateEmployee(dialog.getEmployeeFromForm());
                    refreshTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an employee.");
            }
        });

        // DELETE (action)
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String id = table.getValueAt(selectedRow, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(this, "Delete Employee ID: " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.getEmployeeService().deleteEmployee(id);
                    refreshTable();
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        
        return buttonPanel;
    }

    private void setupTable() {
        // columns (JTable)
        String[] columns = {"ID", "Last Name", "First Name", "Birth Date", "SSS", "Philhealth", "Tin", "Pag-Ibig", "Status", "Position", 
                            "Basic Pay", "Rice Subsidy" ,"Phone Allowance","Clothes Allowance","Hourly Rate"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // table size & style
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // column widths
        int[] widths = {50, 100, 100, 100, 110, 110, 110, 110, 100, 150, 100, 100, 100, 100, 100};

        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i < widths.length) {
                table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
            }
        }
        
        // scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xEEEEEE)));
        
        add(scrollPane, BorderLayout.CENTER);
    }
    
     // pulls the latest list from the csv via the service and updates the ui.

    public void refreshTable() {
        tableModel.setRowCount(0); 
        List<Employee> list = controller.getEmployeeService().getAllEmployees();
               
        for (Employee e : list) {
            tableModel.addRow(new Object[]{
                e.getEmployeeID(), 
                e.getLastName(), 
                e.getFirstName(), 
                e.getBirthDate(),
                e.getSSS(),
                e.getPhilhealth(),
                e.getTin(),
                e.getPagibig(),
                e.getStatus(),
                e.getPosition(), 
                e.getBasicPay(),
                e.getRiceSub(),
                e.getPhoneAl(),
                e.getClothAl(),
                e.getHourlyRate()
            });
        }
    }
}
