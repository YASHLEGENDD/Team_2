package com.employee.management.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employee.management.hrms.entity.LeaveRequest;
import com.employee.management.hrms.entity.LeaveStatus;
@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {

    List<LeaveRequest> findByEmployee_EmployeeId(Integer employeeId);

    List<LeaveRequest> findByStatus(LeaveStatus status);

    List<LeaveRequest> findByApprovedBy_EmployeeId(Integer managerId);

    List<LeaveRequest> findByEmployee_Department_DepartmentId(Integer departmentId);
}