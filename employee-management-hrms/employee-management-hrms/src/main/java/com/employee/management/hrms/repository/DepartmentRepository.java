package com.employee.management.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.hrms.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByDepartmentName(String departmentName);
}

