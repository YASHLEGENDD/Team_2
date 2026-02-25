package com.example.employee.hrms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employee.hrms.DTO.EmployeeRequestDto;
import com.example.employee.hrms.DTO.UserRequestDto;
import com.example.employee.hrms.entity.Employee;
import com.example.employee.hrms.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class AdminController {

    private final EmployeeService employeeService;

    public AdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE USER
    @PostMapping("/create-user")
    public ResponseEntity<Long> createUser(
            @Valid @RequestBody UserRequestDto request) {

        return ResponseEntity.ok(employeeService.createUser(request));
    }

    // CREATE EMPLOYEE
    @PostMapping("/create-employee")
    public ResponseEntity<String> createEmployee(
            @Valid @RequestBody EmployeeRequestDto request) {

        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    //  THIS FIXES YOUR DASHBOARD
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}