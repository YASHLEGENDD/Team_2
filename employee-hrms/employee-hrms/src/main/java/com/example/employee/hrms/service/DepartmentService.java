package com.example.employee.hrms.service;

import java.util.List;

import com.example.employee.hrms.entity.Department;

public interface DepartmentService {

    Department createDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(Long departmentId);

    Department updateDepartment(Long departmentId, Department department);

    void deleteDepartment(Long departmentId);
}

