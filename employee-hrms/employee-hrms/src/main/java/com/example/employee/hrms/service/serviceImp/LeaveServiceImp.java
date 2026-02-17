package com.example.employee.hrms.service.serviceImp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.hrms.entity.Employee;
import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.entity.LeaveStatus;
import com.example.employee.hrms.entity.LeaveType;
import com.example.employee.hrms.repository.EmployeeRepository;
import com.example.employee.hrms.repository.LeaveRequestRepository;
import com.example.employee.hrms.repository.LeaveTypeRepository;
import com.example.employee.hrms.service.LeaveService;

@Service
public class LeaveServiceImp implements LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Override
    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {

        // Validate employee
        Employee employee = employeeRepository.findById(
                leaveRequest.getEmployee().getEmployeeId()
        ).orElseThrow(() -> new RuntimeException("Employee not found"));

        // Validate leave type
        LeaveType leaveType = leaveTypeRepository.findById(
                leaveRequest.getLeaveType().getLeaveTypeId()
        ).orElseThrow(() -> new RuntimeException("Leave type not found"));

        // Validate dates
        if (leaveRequest.getStartDate().isAfter(leaveRequest.getEndDate())) {
            throw new RuntimeException("Start date cannot be after end date");
        }

        if (leaveRequest.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Leave cannot be applied for past dates");
        }

        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(leaveType);

        // Use ENUM (not String)
        leaveRequest.setStatus(LeaveStatus.PENDING);

        // Removed incorrect endDate overwrite

        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest approveLeave(Long leaveRequestId) {

        LeaveRequest leave = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        // Enum comparison
        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException("Only pending leaves can be approved");
        }

        leave.setStatus(LeaveStatus.APPROVED);
        leave.setApprovedDate(LocalDateTime.now());

        return leaveRequestRepository.save(leave);
    }

    @Override
    public LeaveRequest rejectLeave(Long leaveRequestId) {

        LeaveRequest leave = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        // Enum comparison
        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException("Only pending leaves can be rejected");
        }

        leave.setStatus(LeaveStatus.REJECTED);
        leave.setApprovedDate(LocalDateTime.now());

        return leaveRequestRepository.save(leave);
    }

    @Override
    public LeaveRequest getLeaveById(Long leaveRequestId) {
        return leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
    }

    @Override
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> getLeavesByEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return leaveRequestRepository.findByEmployee(employee);
    }
}
