package com.employee.management.hrms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.hrms.entity.Employee;
import com.employee.management.hrms.entity.Department;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Find employee by email (useful for validation/login)
    Optional<Employee> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Get all employees of a department
    List<Employee> findByDepartment(Department department);

    // Get all employees under a manager
    List<Employee> findByManager(Employee manager);

    // Find employees by designation
    List<Employee> findByDesignation(String designation);
}

