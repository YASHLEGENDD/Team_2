package com.employee.management.hrms.dto;

import java.time.LocalDate;

public class EmployeeDTO {
    private Integer employeeId;
    private String name;
    private String email;
    private String phone;
    private String designation;
    private LocalDate joiningDate;
    private Integer departmentId;
    private Integer managerId;

    public EmployeeDTO() {}

    public EmployeeDTO(Integer employeeId, String name, String email,String phone, String designation,LocalDate joiningDate,Integer departmentId,Integer managerId) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.designation = designation;
        this.joiningDate = joiningDate;
        this.departmentId = departmentId;
        this.managerId = managerId;
    }

    // Getters and Setters

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

}
