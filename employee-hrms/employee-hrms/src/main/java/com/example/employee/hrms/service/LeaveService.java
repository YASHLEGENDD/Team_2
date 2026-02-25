package com.example.employee.hrms.service;

import java.util.List;
import com.example.employee.hrms.entity.LeaveRequest;

public interface LeaveService {

    LeaveRequest applyLeave(LeaveRequest leaveRequest, String email);

    List<LeaveRequest> getLeavesByEmail(String email);

    List<LeaveRequest> getLeavesForManager(String managerEmail);

    LeaveRequest approveLeave(Long leaveId, String managerEmail);

    LeaveRequest rejectLeave(Long leaveId, String managerEmail);
}