package com.example.employee.hrms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.service.LeaveService;

@RestController
@RequestMapping("/api/leave")
@CrossOrigin(origins = {"http://localhost:5500","http://127.0.0.1:5500"})
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    // EMPLOYEE APPLY
    @PostMapping("/apply")
    public ResponseEntity<LeaveRequest> applyLeave(
            @RequestBody LeaveRequest leaveRequest,
            Principal principal) {

        return ResponseEntity.ok(
                leaveService.applyLeave(leaveRequest, principal.getName())
        );
    }

    //EMPLOYEE VIEW OWN 
    @GetMapping("/my")
    public ResponseEntity<List<LeaveRequest>> getMyLeaves(
            Principal principal) {

        return ResponseEntity.ok(
                leaveService.getLeavesByEmail(principal.getName())
        );
    }

    //MANAGER VIEW PENDING 
    @GetMapping("/manager/pending")
    public ResponseEntity<List<LeaveRequest>> getManagerLeaves(
            Principal principal) {

        return ResponseEntity.ok(
                leaveService.getLeavesForManager(principal.getName())
        );
    }

    //  APPROVE
    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(
            @PathVariable Long id,
            Principal principal) {

        return ResponseEntity.ok(
                leaveService.approveLeave(id, principal.getName())
        );
    }

    // REJECT 
    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeave(
            @PathVariable Long id,
            Principal principal) {

        return ResponseEntity.ok(
                leaveService.rejectLeave(id, principal.getName())
        );
    }
}