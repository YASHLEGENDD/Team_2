package com.example.employee.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.hrms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByUser_Email(String email);

    Employee findByUser_Email(String email);
}

