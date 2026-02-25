package com.example.employee.hrms.service;

import java.util.List;

import com.example.employee.hrms.DTO.EmployeeRequestDto;
import com.example.employee.hrms.DTO.UserRequestDto;
import com.example.employee.hrms.entity.Employee;

public interface EmployeeService {

    String createEmployee(EmployeeRequestDto request);

    Employee getEmployeeById(Long id);

    Employee getEmployeeByEmail(String email);

    Employee save(Employee employee);

    List<Employee> getAllEmployees();

    Long createUser(UserRequestDto request);
}