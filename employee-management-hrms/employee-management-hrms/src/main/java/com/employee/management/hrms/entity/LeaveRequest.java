package com.employee.management.hrms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveRequestId;

    private LocalDate startDate;
    private LocalDate endDate;

    private String reason;

    private LocalDate appliedDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.PENDING;

    // Many Requests -> One Employee
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Many Requests -> One LeaveType
    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    // Approved by Manager
    @ManyToOne
    @JoinColumn(name = "approved_by")
    private Employee approvedBy;
    
 // Getters & Setters

	public Long getLeaveRequestId() {
		return leaveRequestId;
	}

	public void setLeaveRequestId(Long leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Employee getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Employee approvedBy) {
		this.approvedBy = approvedBy;
	}

    
}