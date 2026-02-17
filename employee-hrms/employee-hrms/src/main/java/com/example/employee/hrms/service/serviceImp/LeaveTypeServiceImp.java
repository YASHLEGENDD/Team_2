package com.example.employee.hrms.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.hrms.entity.LeaveType;
import com.example.employee.hrms.repository.LeaveTypeRepository;
import com.example.employee.hrms.service.LeaveTypeService;
@Service
public class LeaveTypeServiceImp implements LeaveTypeService {

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Override
    public LeaveType createLeaveType(LeaveType leaveType) {

        if (leaveTypeRepository.existsByLeaveName(leaveType.getLeaveName())) {
            throw new RuntimeException("Leave type already exists");
        }

        return leaveTypeRepository.save(leaveType);
    }

    @Override
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    @Override
    public LeaveType getLeaveTypeById(Long leaveTypeId) {
        return leaveTypeRepository.findById(leaveTypeId)
                .orElseThrow(() -> new RuntimeException("Leave type not found"));
    }

    @Override
    public void deleteLeaveType(Long leaveTypeId) {

        LeaveType leaveType = leaveTypeRepository.findById(leaveTypeId)
                .orElseThrow(() -> new RuntimeException("Leave type not found"));

        leaveTypeRepository.delete(leaveType);
    }
}

