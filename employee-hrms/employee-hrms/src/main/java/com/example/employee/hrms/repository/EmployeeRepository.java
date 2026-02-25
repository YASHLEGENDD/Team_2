package com.example.employee.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.employee.hrms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUser_Email(String email);

    Optional<Employee> findByUser_UserId(Long userId);
}