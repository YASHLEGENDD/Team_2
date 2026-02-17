package com.example.employee.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.hrms.entity.Employee;
import com.example.employee.hrms.entity.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(Employee employee);

    List<LeaveRequest> findByStatus(String status);

    List<LeaveRequest> findByEmployee_Manager(Employee manager);
}

