package com.example.employee.hrms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.employee.hrms.entity.Employee;
import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.service.EmployeeService;
import com.example.employee.hrms.service.LeaveService;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class EmployeeController {

    private final LeaveService leaveService;
    private final EmployeeService employeeService;

    public EmployeeController(LeaveService leaveService,
                              EmployeeService employeeService) {
        this.leaveService = leaveService;
        this.employeeService = employeeService;
    }

    // APPLY LEAVE 
    @PostMapping("/apply-leave")
    public ResponseEntity<LeaveRequest> applyLeave(
            @RequestBody LeaveRequest leaveRequest,
            Authentication authentication) {

        String email = authentication.getName();

        LeaveRequest savedLeave =
                leaveService.applyLeave(leaveRequest, email);

        return ResponseEntity.ok(savedLeave);
    }

    // GET MY LEAVES 
    @GetMapping("/my-leaves")
    public ResponseEntity<List<LeaveRequest>> getMyLeaves(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                leaveService.getLeavesByEmail(email));
    }

    // GET MY PROFILE 
    @GetMapping("/profile")
    public ResponseEntity<Employee> getMyProfile(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                employeeService.getEmployeeByEmail(email));
    }

    //  UPDATE PROFILE 
    @PutMapping("/profile")
    public ResponseEntity<Employee> updateProfile(
            @RequestBody Employee updatedEmployee,
            Authentication authentication) {

        String email = authentication.getName();

        Employee employee =
                employeeService.getEmployeeByEmail(email);

        // Allow safe updates only
        employee.getUser().setFullName(
                updatedEmployee.getUser().getFullName());

        employee.getUser().setEmail(
                updatedEmployee.getUser().getEmail());

        return ResponseEntity.ok(
                employeeService.save(employee));
    }
}