package com.example.employee.hrms.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.hrms.entity.Department;
import com.example.employee.hrms.repository.DepartmentRepository;
import com.example.employee.hrms.service.DepartmentService;

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {

        if (departmentRepository.existsByDepartmentName(
                department.getDepartmentName().toUpperCase())) {
            throw new RuntimeException("Department already exists");
        }

        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public Department updateDepartment(Long departmentId, Department updatedDepartment) {

        Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existingDepartment.setDepartmentName(updatedDepartment.getDepartmentName());
        existingDepartment.setDescription(updatedDepartment.getDescription());

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.delete(department);
    }
}


