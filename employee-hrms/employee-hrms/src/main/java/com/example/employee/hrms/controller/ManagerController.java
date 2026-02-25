package com.example.employee.hrms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.service.LeaveService;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin(origins = "*")
public class ManagerController {

    private final LeaveService leaveService;

    public ManagerController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    //  APPROVE LEAVE 
    @PutMapping("/approve/{leaveId}")
    public ResponseEntity<LeaveRequest> approveLeave(
            @PathVariable Long leaveId,
            Authentication authentication) {

        String managerEmail = authentication.getName();

        return ResponseEntity.ok(
                leaveService.approveLeave(leaveId, managerEmail)
        );
    }

    // REJECT LEAVE 
    @PutMapping("/reject/{leaveId}")
    public ResponseEntity<LeaveRequest> rejectLeave(
            @PathVariable Long leaveId,
            Authentication authentication) {

        String managerEmail = authentication.getName();

        return ResponseEntity.ok(
                leaveService.rejectLeave(leaveId, managerEmail)
        );
    }

    //  VIEW MY TEAM LEAVES 
    @GetMapping("/team-leaves")
    public ResponseEntity<List<LeaveRequest>> getTeamLeaves(
            Authentication authentication) {

        String managerEmail = authentication.getName();

        return ResponseEntity.ok(
                leaveService.getLeavesForManager(managerEmail)
        );
    }
}