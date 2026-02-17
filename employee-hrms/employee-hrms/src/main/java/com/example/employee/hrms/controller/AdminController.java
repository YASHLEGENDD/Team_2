package com.example.employee.hrms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employee.hrms.entity.Department;
import com.example.employee.hrms.entity.Employee;
import com.example.employee.hrms.entity.LeaveType;
import com.example.employee.hrms.service.DepartmentService;
import com.example.employee.hrms.service.EmployeeService;
import com.example.employee.hrms.service.LeaveTypeService;

import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final LeaveTypeService leaveTypeService;

    public AdminController(
            DepartmentService departmentService,
            EmployeeService employeeService,
            LeaveTypeService leaveTypeService) {

        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.leaveTypeService = leaveTypeService;
    }

    // CREATE DEPARTMENT
    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(
            @RequestBody Department department) {

        return ResponseEntity.ok(
                departmentService.createDepartment(department)
        );
    }

    // GET ALL DEPARTMENTS  ADDED
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {

        return ResponseEntity.ok(
                departmentService.getAllDepartments()
        );
    }

    // CREATE EMPLOYEE
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee) {

        return ResponseEntity.ok(
                employeeService.createEmployee(employee)
        );
    }

    // GET ALL EMPLOYEES
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }

    // CREATE LEAVE TYPE
    @PostMapping("/leave-types")
    public ResponseEntity<LeaveType> createLeaveType(
            @RequestBody LeaveType leaveType) {

        return ResponseEntity.ok(
                leaveTypeService.createLeaveType(leaveType)
        );
    }
}
