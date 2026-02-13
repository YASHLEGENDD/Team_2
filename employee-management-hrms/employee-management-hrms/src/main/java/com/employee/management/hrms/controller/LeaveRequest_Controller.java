package com.employee.management.hrms.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.employee.management.hrms.dto.LeaveRequestDto;

import com.employee.management.hrms.entity.LeaveRequest;
import com.employee.management.hrms.service.LeaveRequest_Service;
import com.employee.management.hrms.service.serviceImp.LeaveRequest_serviceImp;


@RestController
@RequestMapping("/api/LeaveRequest")	//base url
public class LeaveRequest_Controller {
 private LeaveRequest_Controller leave;
 
 
 @Autowired
	private LeaveRequest_Controller(LeaveRequest_serviceImp service) {
		this.leave = leave;
	}
//register user
	@PostMapping
	public ResponseEntity<LeaveRequestDto> saveUser(@RequestBody LeaveRequestDto dto) {
		ResponseEntity<LeaveRequestDto>resp ;
		resp =new ResponseEntity<>(LeaveRequest_Service.createLeave(dto),HttpStatus.CREATED);
		
		return resp;
	}
	
	@GetMapping("/{id}")
	//get user by id
	public ResponseEntity<LeaveRequestDto>gerUserById(@PathVariable Integer  leaveId){
		return new ResponseEntity<>(LeaveRequest_Service.getLeaveByleaveId(leaveId),HttpStatus.OK);
	}
	
	@GetMapping
	//get all users 
	public ResponseEntity<List<LeaveRequestDto>>getAllUsers(){
		return new ResponseEntity<>(LeaveRequest_Service.getAllLeave(),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	//update user
	public ResponseEntity<LeaveRequestDto>updateUser(@PathVariable Integer id,@RequestBody LeaveRequestDto dto){
		return new ResponseEntity<>(LeaveRequest_Service.updateLeave(leave,dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	//delete user
	 public ResponseEntity<String>deleteuser(@PathVariable Integer id){
		LeaveRequest_Service.deleteLeave(id);
		return new ResponseEntity<>("User Deleted successfully!",HttpStatus.OK);
	}
	
}