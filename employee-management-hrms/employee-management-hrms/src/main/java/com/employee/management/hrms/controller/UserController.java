package com.employee.management.hrms.controller;

import java.util.List;

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
import com.employee.management.hrms.dto.UserDto;
import com.employee.management.hrms.service.serviceImp.UserServiceImp;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserServiceImp service;
    
    @Autowired
    public UserController(UserServiceImp service) {
    	this.service = service;
    }

    //Register  User
    @PostMapping
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto dto) {
		ResponseEntity<UserDto> resp;
		resp = new ResponseEntity<>(service.createUser(dto),HttpStatus.CREATED);
		return resp;
	}
	
	//Get User By ID
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
		return new ResponseEntity<>(service.getUserById(id),HttpStatus.OK);
	}
	
	//Get All Users
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUser(){
		return new ResponseEntity<>(service.getAllUser(),HttpStatus.OK);
	}
	
	//Update User
	@PutMapping
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer Id, @RequestBody UserDto dto){
		return new ResponseEntity<>(service.UpadateUser(Id, dto), HttpStatus.OK);
	}
	
	//Delete User
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletUser(@PathVariable Integer id){
		service.deleteUser(id);
		return new ResponseEntity<>("User Delete Successfully!", HttpStatus.OK);
	}

   

}
