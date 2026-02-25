package com.example.employee.hrms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @NotBlank(message = "Employee code is required")
    @Column(name = "employee_code", nullable = false, unique = true)
    private String employeeCode;

    @NotBlank(message = "Designation is required")
    @Column(name = "designation", nullable = false)
    private String designation;

    @NotNull(message = "Joining date is required")
    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // Self reference (Manager)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    // ðŸ”´ IGNORE to prevent infinite recursion
    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    private List<Employee> subordinates;

    // ðŸ”´ IGNORE to prevent infinite recursion
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<LeaveRequest> leaveRequests;

    //  CONSTRUCTORS 

    public Employee() {
    }

    public Employee(User user, String employeeCode, String designation,
                    LocalDate joiningDate, Department department) {
        this.user = user;
        this.employeeCode = employeeCode;
        this.designation = designation;
        this.joiningDate = joiningDate;
        this.department = department;
    }

    //  LIFECYCLE 

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // GETTERS & SETTERS

    public Long getEmployeeId() {
        return employeeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
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

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }
}