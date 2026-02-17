package com.example.employee.hrms.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
    name = "departments",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "department_name")
    }
)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @NotBlank(message = "Department name is required")
    @Column(name = "department_name", nullable = false, unique = true)
    private String departmentName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    // Constructors

    public Department() {
    }

    public Department(String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.departmentName != null) {
            this.departmentName = this.departmentName.toUpperCase();
        }
    }

    // Getters and Setters

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName != null ? departmentName.toUpperCase() : null;
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

    public List<Employee> getEmployees() {
        return employees;
    }
}

