package com.example.employee.hrms.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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

    // ðŸ”´ VERY IMPORTANT: Prevent infinite recursion
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employees;

    // CONSTRUCTORS 

    public Department() {
    }

    public Department(String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
    }

    //  LIFECYCLE 

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.departmentName != null) {
            this.departmentName = this.departmentName.toUpperCase();
        }
    }

    // GETTERS & SETTERS 

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName =
                departmentName != null ? departmentName.toUpperCase() : null;
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