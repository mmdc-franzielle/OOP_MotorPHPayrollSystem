/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.tools;

import dao.AttendanceDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.Attendance;
import java.util.List;

/**
 *
 * @author franzielle
 */

public class TimesheetPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public TimesheetPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Timesheet");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

    
        String[] columns = {"Employee ID", "Last Name", "FirstName", "Date", "Time In", "Time Out", "Hours Worked", "Late (Mins)"};
        
       
        tableModel = new DefaultTableModel(columns, 0) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        loadAllData();

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadAllData() {
        AttendanceDAO dao = new AttendanceDAO();
        List<Attendance> list = dao.getAllAttendance();
        
     
        tableModel.setRowCount(0);

        for (Attendance a : list) {
            tableModel.addRow(new Object[]{
                a.getEmployeeID(), 
                a.getLastName(),
                a.getFirstName(),
                a.getDate(),            
                a.getTimeIn(),          
                a.getTimeOut(),         
                String.format("%.2f", a.calculateDayHours()),
                (int)a.calculateLateMinutes()                 
            });
        }
    }
}