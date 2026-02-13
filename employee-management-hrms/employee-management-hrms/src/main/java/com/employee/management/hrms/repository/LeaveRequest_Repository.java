package com.employee.management.hrms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.hrms.entity.Employee;
import com.employee.management.hrms.entity.LeaveRequest;
import com.employee.management.hrms.entity.LeaveStatus;
import com.employee.management.hrms.entity.LeaveType;

@Repository
public interface LeaveRequest_Repository extends JpaRepository<LeaveRequest, Integer> {

    // 📌 Get all leave requests of a specific employee
    List<LeaveRequest> findByEmployee(Employee employee);

    // 📌 Get leave requests by status (PENDING, APPROVED, REJECTED)
    List<LeaveRequest> findByStatus(LeaveStatus status);

    // 📌 Get leave requests by type (CASUAL, SICK, etc.)
    List<LeaveRequest> findByLeaveType(LeaveType leaveType);

    // 📌 Get leave requests of an employee by status
    List<LeaveRequest> findByEmployeeAndStatus(Employee employee, LeaveStatus status);

    // 📌 Get leave requests between dates
    List<LeaveRequest> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    // 📌 Get leave requests of employee within date range
    List<LeaveRequest> findByEmployeeAndStartDateBetween(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate
    );
}
