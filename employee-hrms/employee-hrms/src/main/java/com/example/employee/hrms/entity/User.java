package com.example.employee.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Full name is required")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Role is required")
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "account_status", nullable = false)
    private String accountStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Constructors

    public User() {
    }

    public User(String fullName, String email, String password,
                String role, String accountStatus) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        setRole(role);
        setAccountStatus(accountStatus);
    }

    // JPA Lifecycle Hook

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.accountStatus == null) {
            this.accountStatus = "ACTIVE";
        }

        if (this.role != null) {
            this.role = normalizeRole(this.role);
        }
    }

    // Utility Method

    private String normalizeRole(String role) {
        if (role == null) return null;
        return role.toUpperCase().replace("ROLE_", "");
    }

    // Getters & Setters

    public Long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    // Password should already be encoded before setting
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = normalizeRole(role);
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus != null
                ? accountStatus.toUpperCase()
                : "ACTIVE";
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
