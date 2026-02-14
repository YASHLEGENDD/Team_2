package com.employee.management.hrms.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "leave_types")
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveTypeId;

    private String leaveName; // Casual, Sick, Earned

    private int maxDaysPerYear;

    @OneToMany
    private List<LeaveRequest> leaveRequests;
    
 // Getters & Setters

	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public int getMaxDaysPerYear() {
		return maxDaysPerYear;
	}

	public void setMaxDaysPerYear(int maxDaysPerYear) {
		this.maxDaysPerYear = maxDaysPerYear;
	}

	public List<LeaveRequest> getLeaveRequests() {
		return leaveRequests;
	}

	public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
		this.leaveRequests = leaveRequests;
	}

    
}