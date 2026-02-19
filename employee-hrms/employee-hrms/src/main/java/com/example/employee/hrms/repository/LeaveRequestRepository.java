package com.example.employee.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.entity.LeaveStatus;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee_EmployeeId(Long employeeId);

    List<LeaveRequest> findByStatus(LeaveStatus status);
}
