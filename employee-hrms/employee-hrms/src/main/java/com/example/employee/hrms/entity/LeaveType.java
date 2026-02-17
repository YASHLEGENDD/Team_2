package com.example.employee.hrms.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
    name = "leave_types",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "leave_name")
    }
)
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id")
    private Long leaveTypeId;

    @NotBlank(message = "Leave name is required")
    @Column(name = "leave_name", nullable = false, unique = true)
    private String leaveName;

    @Column(name = "max_days_per_year", nullable = false)
    private Integer maxDaysPerYear;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Constructors

    public LeaveType() {
    }

    public LeaveType(String leaveName, Integer maxDaysPerYear, String description) {
        this.leaveName = leaveName;
        this.maxDaysPerYear = maxDaysPerYear;
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.leaveName != null) {
            this.leaveName = this.leaveName.toUpperCase();
        }
    }

    // Getters and Setters

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName != null ? leaveName.toUpperCase() : null;
    }

    public Integer getMaxDaysPerYear() {
        return maxDaysPerYear;
    }

    public void setMaxDaysPerYear(Integer maxDaysPerYear) {
        this.maxDaysPerYear = maxDaysPerYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}


