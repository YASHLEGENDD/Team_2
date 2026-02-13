
	package com.employee.management.hrms.dto;

	import java.time.LocalDate;
	import java.time.LocalDateTime;

	import com.employee.management.hrms.entity.LeaveStatus;
	import com.employee.management.hrms.entity.LeaveType;
	
	public class LeaveRequestDto {

	
	    private Integer leaveId;

	    private Integer employeeId;
	    private String employeeName;

	    private LeaveType leaveType;
	    private LocalDate startDate;
	    private LocalDate endDate;

	    private LeaveStatus status;
	    private String reason;

	    private LocalDateTime appliedAt;

	    public LeaveRequestDto() {}

	    // Getters and Setters

	    public Integer getLeaveId() {
	        return leaveId;
	    }

	    public void setLeaveId(Integer leaveId) {
	        this.leaveId = leaveId;
	    }

	    public Integer getEmployeeId() {
	        return employeeId;
	    }

	    public void setEmployeeId(Integer employeeId) {
	        this.employeeId = employeeId;
	    }

	    public String getEmployeeName() {
	        return employeeName;
	    }

	    public void setEmployeeName(String employeeName) {
	        this.employeeName = employeeName;
	    }

	    public LeaveType getLeaveType() {
	        return leaveType;
	    }

	    public void setLeaveType(LeaveType leaveType) {
	        this.leaveType = leaveType;
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

	    public LeaveStatus getStatus() {
	        return status;
	    }

	    public void setStatus(LeaveStatus status) {
	        this.status = status;
	    }

	    public String getReason() {
	        return reason;
	    }

	    public void setReason(String reason) {
	        this.reason = reason;
	    }

	    public LocalDateTime getAppliedAt() {
	        return appliedAt;
	    }

	    public void setAppliedAt(LocalDateTime appliedAt) {
	        this.appliedAt = appliedAt;
	    }
	}