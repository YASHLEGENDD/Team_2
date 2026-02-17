package com.example.employee.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.hrms.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentName(String departmentName);

    boolean existsByDepartmentName(String departmentName);
}

