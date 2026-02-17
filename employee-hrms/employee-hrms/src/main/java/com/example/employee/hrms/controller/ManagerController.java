package com.example.employee.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.entity.LeaveStatus;
import com.example.employee.hrms.service.LeaveService;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin(origins = "*") // Allow frontend access
public class ManagerController {

    @Autowired
    private LeaveService leaveService;

    // Approve Leave
    @PutMapping("/approve/{leaveId}")
    public ResponseEntity<LeaveRequest> approveLeave(
            @PathVariable Long leaveId) {
        return ResponseEntity.ok(
                leaveService.approveLeave(leaveId));
    }

    // Reject Leave
    @PutMapping("/reject/{leaveId}")
    public ResponseEntity<LeaveRequest> rejectLeave(
            @PathVariable Long leaveId) {
        return ResponseEntity.ok(
                leaveService.rejectLeave(leaveId));
    }

    // View Pending Leaves
    @GetMapping("/pending")
    public ResponseEntity<List<LeaveRequest>> getPendingLeaves() {
        return ResponseEntity.ok(
                leaveService.getAllLeaves().stream()
                        .filter(l -> l.getStatus() == LeaveStatus.PENDING)
                        .toList()
        );
    }
}
