package com.employee.management.hrms.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.hrms.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmployeeCode(String employeeCode);

    List<Employee> findByDepartment_DepartmentId(Long departmentId);

    List<Employee> findByManager_EmployeeId(Long managerId);
}