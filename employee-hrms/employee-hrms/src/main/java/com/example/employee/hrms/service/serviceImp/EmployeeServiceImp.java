package com.example.employee.hrms.service.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.hrms.entity.Employee;
import com.example.employee.hrms.exception.ResourceNotFoundException;
import com.example.employee.hrms.repository.EmployeeRepository;
import com.example.employee.hrms.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Create Employee
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get Employee By ID
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Employee not found with id: " + id));
    }

    // Get All Employees
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Update Employee
    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Employee not found with id: " + id));

        existingEmployee.setEmployeeCode(updatedEmployee.getEmployeeCode());
        existingEmployee.setDesignation(updatedEmployee.getDesignation());
        existingEmployee.setJoiningDate(updatedEmployee.getJoiningDate());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setManager(updatedEmployee.getManager());
        existingEmployee.setUser(updatedEmployee.getUser());

        return employeeRepository.save(existingEmployee);
    }

    // Delete Employee
    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }
}
