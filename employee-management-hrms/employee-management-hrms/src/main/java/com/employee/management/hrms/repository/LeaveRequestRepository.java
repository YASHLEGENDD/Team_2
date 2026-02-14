package com.employee.management.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.hrms.entity.LeaveRequest;
import com.employee.management.hrms.entity.LeaveStatus;
@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee_EmployeeId(Long employeeId);

    List<LeaveRequest> findByStatus(LeaveStatus status);

    List<LeaveRequest> findByApprovedBy_EmployeeId(Long managerId);

    List<LeaveRequest> findByEmployee_Department_DepartmentId(Long departmentId);
}
