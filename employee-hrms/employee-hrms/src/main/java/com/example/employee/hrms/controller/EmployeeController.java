package com.example.employee.hrms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.employee.hrms.entity.Employee;
import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.service.EmployeeService;
import com.example.employee.hrms.service.LeaveService;

@RestController
@RequestMapping("/api/employee")
@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeController {

    private final LeaveService leaveService;
    private final EmployeeService employeeService;

    public EmployeeController(LeaveService leaveService,
                              EmployeeService employeeService) {
        this.leaveService = leaveService;
        this.employeeService = employeeService;
    }

    // ===================== APPLY LEAVE =====================
    @PostMapping("/apply-leave")
    public ResponseEntity<LeaveRequest> applyLeave(
            @RequestBody LeaveRequest leaveRequest) {
        return ResponseEntity.ok(leaveService.applyLeave(leaveRequest));
    }

    // ===================== GET MY LEAVES =====================
    @GetMapping("/leaves/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getMyLeaves(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(
                leaveService.getLeavesByEmployee(employeeId));
    }

    // ===================== UPDATE PROFILE =====================
    @PutMapping("/profile/{id}")
    public ResponseEntity<Employee> updateProfile(
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee) {

        Employee employee = employeeService.getEmployeeById(id);

        employee.setDepartment(updatedEmployee.getDepartment());
        employee.getUser().setEmail(updatedEmployee.getUser().getEmail());
        employee.getUser().setFullName(updatedEmployee.getUser().getFullName());

        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }
}
