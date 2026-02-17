package com.example.employee.hrms.service;

import java.util.List;

import com.example.employee.hrms.entity.Employee;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId);

    Employee updateEmployee(Long employeeId, Employee employee);

    void deleteEmployee(Long employeeId);
}


