package com.employee.management.hrms.entity;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String employeeCode;

    private String designation;

    private LocalDate joiningDate;

    // One-to-One User
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Many Employees -> One Department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Self Referencing Manager
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    // Employees under Manager
    @OneToMany
    private List<Employee> subordinates;

    // Leave Requests
    @OneToMany
    private List<LeaveRequest> leaveRequests;
    
    //// Getters & Setters

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public List<LeaveRequest> getLeaveRequests() {
		return leaveRequests;
	}

	public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
		this.leaveRequests = leaveRequests;
	}

    
}