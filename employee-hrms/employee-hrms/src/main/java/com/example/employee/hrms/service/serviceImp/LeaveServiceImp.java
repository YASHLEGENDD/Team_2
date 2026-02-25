package com.example.employee.hrms.service.serviceImp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.hrms.entity.Employee;
import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.entity.LeaveStatus;
import com.example.employee.hrms.repository.EmployeeRepository;
import com.example.employee.hrms.repository.LeaveRepository;
import com.example.employee.hrms.service.LeaveService;

@Service
public class LeaveServiceImp implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveServiceImp(LeaveRepository leaveRepository,
                           EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    // APPLY LEAVE
    @Override
    @Transactional
    public LeaveRequest applyLeave(LeaveRequest leaveRequest, String email) {

        Employee employee = employeeRepository
                .findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        leaveRequest.setEmployee(employee);
       // leaveRequest.setAppliedDate(LocalDateTime.now());
        leaveRequest.setStatus(LeaveStatus.PENDING);

        return leaveRepository.save(leaveRequest);
    }

    //GET MY LEAVES
    @Override
    public List<LeaveRequest> getLeavesByEmail(String email) {

        Employee employee = employeeRepository
                .findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return leaveRepository.findByEmployee(employee);
    }

    //  GET LEAVES FOR MANAGER 
    @Override
    public List<LeaveRequest> getLeavesForManager(String managerEmail) {

        Employee manager = employeeRepository
                .findByUser_Email(managerEmail)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        return leaveRepository.findByEmployee_ManagerAndStatus(manager, LeaveStatus.PENDING);
    }

    //  APPROVE LEAVE 
    @Override
    @Transactional
    public LeaveRequest approveLeave(Long leaveId, String managerEmail) {

        Employee manager = employeeRepository
                .findByUser_Email(managerEmail)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        LeaveRequest leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        Employee employeeManager = leave.getEmployee().getManager();

        if (employeeManager == null ||
            !employeeManager.getEmployeeId().equals(manager.getEmployeeId())) {

            throw new RuntimeException("Not authorized to approve this leave");
        }

        leave.setStatus(LeaveStatus.APPROVED);
        leave.setApprovedBy(manager.getEmployeeId());
        leave.setApprovedDate(LocalDateTime.now());

        return leaveRepository.save(leave);
    }

    //  REJECT LEAVE 
    @Override
    @Transactional
    public LeaveRequest rejectLeave(Long leaveId, String managerEmail) {

        Employee manager = employeeRepository
                .findByUser_Email(managerEmail)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        LeaveRequest leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        Employee employeeManager = leave.getEmployee().getManager();

        if (employeeManager == null ||
            !employeeManager.getEmployeeId().equals(manager.getEmployeeId())) {

            throw new RuntimeException("Not authorized to reject this leave");
        }

        leave.setStatus(LeaveStatus.REJECTED);
        leave.setApprovedBy(manager.getEmployeeId());
        leave.setApprovedDate(LocalDateTime.now());

        return leaveRepository.save(leave);
    }
}