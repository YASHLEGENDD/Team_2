package com.employee.management.hrms.entity;

public enum LeaveType {

    CASUAL("Casual Leave", "Short-term personal leave"),
    SICK("Sick Leave", "Medical or health-related leave"),
    PAID("Paid Leave", "Official paid leave"),
    UNPAID("Unpaid Leave", "Leave without salary deduction benefits"),
    MATERNITY("Maternity Leave", "Leave for childbirth and recovery"),
    PATERNITY("Paternity Leave", "Leave for father after childbirth");

    private final String displayName;
    private final String description;

    // Constructor
    LeaveType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    // Getters
    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    // Utility method to convert String to Enum safely
    public static LeaveType fromString(String value) {
        for (LeaveType type : LeaveType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Leave Type: " + value);
    }
}
