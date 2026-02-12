package com.employee.management.hrms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
		private UserServiceImp service;
		
		public UserController(UserService service) {
			this.service = service;
		}
		
		@PostMapping
		public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO dto){
			ResponseEntity<UserDTO> resp;
			resp = new ResponseEntity<>(service.createUser(dto),HttpStatus.CREATED);
			return repo;
			
		}
		
		//get User By ID
		@GetMapping("/{id}")
		public ResponseEntity<UseDTO> getUserById(@PathVariable Integer id){
			return new ResponseEntity<>(service.getUserById(id),HttpStatus.OK);
		}
		
		//Update User
		@GetMapping
		public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequsetBody UserDTO dto){
			return new ResponseEntity<>(service.updateUser(Id, dto), HttpStatus.OK);
		}
		
		//Delete User
		@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteUser(@PathVariable Integer id){
		service.deleteUser(id);
		return new ResponseEntity<>("User Delete Suceessfully!",HttpStatus.OK);
		}

	}

}
