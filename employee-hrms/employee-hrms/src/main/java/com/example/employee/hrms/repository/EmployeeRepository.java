package com.example.employee.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.employee.hrms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUser_Email(String email);
}
