package com.example.employee.hrms.service;

import java.util.List;

import com.example.employee.hrms.entity.LeaveRequest;

public interface LeaveService {

    LeaveRequest applyLeave(LeaveRequest leaveRequest);

    LeaveRequest approveLeave(Long leaveRequestId);

    LeaveRequest rejectLeave(Long leaveRequestId);

    LeaveRequest getLeaveById(Long leaveRequestId);

    List<LeaveRequest> getAllLeaves();

    List<LeaveRequest> getLeavesByEmployee(Long employeeId);

}

