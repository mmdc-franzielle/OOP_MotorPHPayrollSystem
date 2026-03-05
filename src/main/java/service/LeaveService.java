/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.LeaveDAO;
import model.LeaveRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author franzielle
 */

public class LeaveService {
    
   private LeaveDAO leaveDAO = new LeaveDAO();

    // employee action (request for leave)
    public void applyForLeave(LeaveRequest request) {
        List<LeaveRequest> all = leaveDAO.getAllRequests();
        all.add(request);
        leaveDAO.saveRequests(all);
    }

    public List<LeaveRequest> getEmployeeLeaveHistory(String empId) {
        return leaveDAO.getAllRequests().stream()
                .filter(r -> r.getEmployeeId().equals(empId))
                .collect(Collectors.toList());
    }

    // admin / hr action (get leave requests & update status)
    public List<LeaveRequest> getPendingRequests() {
        return leaveDAO.getAllRequests().stream()
                .filter(r -> r.getStatus().equalsIgnoreCase("Pending"))
                .collect(Collectors.toList());
    }

    public void updateRequestStatus(String requestId, String newStatus) {
        List<LeaveRequest> all = leaveDAO.getAllRequests();
        for (LeaveRequest r : all) {
            if (r.getRequestId().equals(requestId)) {
                r.setStatus(newStatus);
                break;
            }
        }
        leaveDAO.saveRequests(all);
    }
}
