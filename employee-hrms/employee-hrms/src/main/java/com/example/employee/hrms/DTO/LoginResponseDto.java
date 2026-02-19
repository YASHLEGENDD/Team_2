package com.example.employee.hrms.DTO;

import java.time.LocalDateTime;

public class LoginResponseDto {

    private String token;
    private String tokenType = "Bearer";

    // ✅ FIX: employeeId (NOT userId)
    private Long employeeId;

    private String fullName;
    private String email;
    private String role;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    public LoginResponseDto() {}

    public LoginResponseDto(String token,
                            Long employeeId,
                            String fullName,
                            String email,
                            String role,
                            LocalDateTime issuedAt,
                            LocalDateTime expiresAt) {

        this.token = token;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    // ================= GETTERS & SETTERS =================

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
