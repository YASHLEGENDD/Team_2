package com.employee.management.hrms.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.hrms.entity.LeaveRequest;
import com.employee.management.hrms.repository.LeaveRequest_Repository;
import com.employee.management.hrms.service.LeaveRequest_Service;


@Service
public class LeaveRequest_serviceImp {
  
	private LeaveRequest_Repository service;
	
	@Autowired
	public LeaveRequest_serviceImp( LeaveRequest_Repository service) {
		this.service=service;
	}
	//insert
	public  LeaveRequest addappliedAt( LeaveRequest l) {
		return service.save(l);
	}
	
	public  LeaveRequest addemployee( LeaveRequest l) {
		return service.save(l);
	}
	public  LeaveRequest addendDate( LeaveRequest l) {
		return service.save(l);
	}
	public  LeaveRequest addleaveId( LeaveRequest l) {
		return service.save(l);
	}
	public  LeaveRequest addleaveType( LeaveRequest l) {
		return service.save(l);
	}
	public  LeaveRequest addreason( LeaveRequest l) {
		return service.save(l);
	}
	public  LeaveRequest addstartDate( LeaveRequest l) {
		return service.save(l);
	}
	public  LeaveRequest addstatus( LeaveRequest l) {
		return service.save(l);
	}
	
  public List<LeaveRequest>getAll(){
	  return service.findAll();
  }
  
}
