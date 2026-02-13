package com.employee.management.hrms.dto;


import java.time.LocalDate;

import com.employee.management.hrms.entity.LeaveType;


import lombok.NonNull;

public class LeaveResponseDto {

    @NonNull
    private Integer employeeId;

    @NonNull
    private LeaveType leaveType;

    @NonNull
    private LocalDate startDate;

    @NonNull
    private LocalDate endDate;

    private String reason;

    public LeaveResponseDto() {}

    // Getters and Setters

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}