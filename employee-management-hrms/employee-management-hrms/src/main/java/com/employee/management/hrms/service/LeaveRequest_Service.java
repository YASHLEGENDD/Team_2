package com.employee.management.hrms.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.hrms.controller.LeaveRequest_Controller;
import com.employee.management.hrms.dto.LeaveRequestDto;
import com.employee.management.hrms.service.serviceImp.LeaveRequest_serviceImp;


public interface LeaveRequest_Service extends JpaRepository<LeaveRequest_serviceImp, Integer>{
	//interface of LeaveRequest extends leaveRequest_serviceImp class
static LeaveRequestDto createLeave(LeaveRequestDto dto) {
	// TODO Auto-generated method stub
	return null;
}
static List<LeaveRequestDto >getAllLeave() {
	// TODO Auto-generated method stub
	return null;
}
static LeaveRequestDto updateLeave(LeaveRequest_Controller leave,LeaveRequestDto dto) {
	// TODO Auto-generated method stub
	return null;
}
static void deleteLeave(Integer leaveId) {
	// TODO Auto-generated method stub
	

 
	// TODO Auto-generated method stub
	return;
}
static void getLeaveByleaveId(Integer leaveId) {
	// TODO Auto-generated method stub
	return ;
}
}
