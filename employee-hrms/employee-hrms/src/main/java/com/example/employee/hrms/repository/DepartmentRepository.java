package com.example.employee.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.employee.hrms.entity.Department;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {

    boolean existsByDepartmentName(String departmentName);
}