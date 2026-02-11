package com.employee.management.hrms.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.hrms.entity.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // Find department by name
    Optional<Department> findByDepartmentName(String departmentName);

    // Check if department already exists
    boolean existsByDepartmentName(String departmentName);
}

