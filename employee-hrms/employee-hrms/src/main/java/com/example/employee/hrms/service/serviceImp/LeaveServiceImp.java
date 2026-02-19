package com.example.employee.hrms.service.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.hrms.entity.Employee;
import com.example.employee.hrms.entity.LeaveRequest;
import com.example.employee.hrms.entity.LeaveStatus;
import com.example.employee.hrms.entity.LeaveType;
import com.example.employee.hrms.exception.ResourceNotFoundException;
import com.example.employee.hrms.repository.EmployeeRepository;
import com.example.employee.hrms.repository.LeaveRequestRepository;
import com.example.employee.hrms.repository.LeaveTypeRepository;
import com.example.employee.hrms.service.LeaveService;

@Service
@Transactional
public class LeaveServiceImp implements LeaveService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveServiceImp(
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository,
            LeaveTypeRepository leaveTypeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {

        Long employeeId = leaveRequest.getEmployee().getEmployeeId();
        Long leaveTypeId = leaveRequest.getLeaveType().getLeaveTypeId();

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        LeaveType leaveType = leaveTypeRepository.findById(leaveTypeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave Type not found"));

        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setStatus(LeaveStatus.PENDING);

        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public List<LeaveRequest> getLeavesByEmployee(Long employeeId) {

        return leaveRequestRepository
                .findByEmployee_EmployeeId(employeeId);
    }

	@Override
	public LeaveRequest approveLeave(Long leaveRequestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LeaveRequest rejectLeave(Long leaveRequestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LeaveRequest getLeaveById(Long leaveRequestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LeaveRequest> getAllLeaves() {
		// TODO Auto-generated method stub
		return null;
	}
}
