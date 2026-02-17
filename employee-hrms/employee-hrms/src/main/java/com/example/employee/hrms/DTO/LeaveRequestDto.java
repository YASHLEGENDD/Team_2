package com.example.employee.hrms.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class LeaveRequestDto {

    private Long leaveRequestId;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Leave Type ID is required")
    private Long leaveTypeId;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    private String reason;

    private String status; // PENDING, APPROVED, REJECTED

    private LocalDate appliedDate;

    // Constructors

    public LeaveRequestDto() {
    }

    public LeaveRequestDto(Long leaveRequestId, Long employeeId, Long leaveTypeId,
                           LocalDate startDate, LocalDate endDate,
                           String reason, String status, LocalDate appliedDate) {
        this.leaveRequestId = leaveRequestId;
        this.employeeId = employeeId;
        this.leaveTypeId = leaveTypeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    // Getters and Setters

    public Long getLeaveRequestId() {
        return leaveRequestId;
    }

    public void setLeaveRequestId(Long leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
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

    public String getStatus() {
        return status;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }
}


