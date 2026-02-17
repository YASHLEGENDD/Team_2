package com.example.employee.hrms.service;

import java.util.List;

import com.example.employee.hrms.entity.LeaveType;

public interface LeaveTypeService {

    LeaveType createLeaveType(LeaveType leaveType);

    List<LeaveType> getAllLeaveTypes();

    LeaveType getLeaveTypeById(Long leaveTypeId);

    void deleteLeaveType(Long leaveTypeId);
}
