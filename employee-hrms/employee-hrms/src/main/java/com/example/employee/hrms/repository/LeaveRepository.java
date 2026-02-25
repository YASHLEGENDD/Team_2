package com.example.employee.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.entity.LeaveStatus;
import com.example.employee.hrms.entity.Employee;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(Employee employee);

    List<LeaveRequest> findByEmployee_ManagerAndStatus(Employee manager, LeaveStatus status);
}