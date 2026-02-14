package com.employee.management.hrms.service.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.employee.management.hrms.dto.UserDto;
import com.employee.management.hrms.entity.User;
import com.employee.management.hrms.repository.UserRepository;
import com.employee.management.hrms.service.UserService;


public class UserServiceImp implements UserService {
	private UserRepository userRepository;
	
	@Autowired //construction injection
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// create method to map/ convert entity to dto
		private UserDto mapToDto(User user) {
			UserDto dto = new UserDto();
			dto.setUserId(user.getUserId());
			dto.setFullName(user.getFullName());
			dto.setEmail(user.getEmail());
			dto.setPassword(user.getPassword());
			dto.setRole(user.getRole());
			dto.setAccountStatus(user.getAccountStatus());
			
			return dto;
		}

		//convert entity to dto
		@Override
		public UserDto createUser(UserDto dto) {
			//request receive
			//convert DTO --> Entity
			User user = new User();
			user.setUserId(dto.getUserId());
			user.setFullName(dto.getFullName());
			user.setEmail(dto.getEmail());
			user.setPassword(dto.getPassword());
			user.setRole(dto.getRole());
			user.setAccountStatus(dto.getAccountStatus());
			user = userRepository.save(user);
		   return mapToDto(userRepository.save(user));
		}

		@Override
		public UserDto getUserById(Integer id) {
			
			return mapToDto(userRepository.findById(id).get());
		}

		@Override
		public List<UserDto> getAllUser() {
			List<User> users = userRepository.findAll();
			//DTOs
			List<UserDto> userDtos = new ArrayList<>();
			
			for(User user : users) {
				userDtos.add(mapToDto(user));
			}
			return userDtos;
		}

		@Override
		public UserDto UpadateUser(Integer id, UserDto dto) {
			//Fetch weather the user present or not in table or database
			User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found!"));
			user.setUserId(dto.getUserId());
			user.setFullName(dto.getFullName());
			user.setEmail(dto.getEmail());
			user.setPassword(dto.getPassword());
			user.setRole(dto.getRole());
			user.setAccountStatus(dto.getAccountStatus());
		
			return mapToDto(userRepository.save(user));
		}

		@Override
		public void deleteUser(Integer id) {
			User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found!"));
	        
			userRepository.delete(user);
			
		}
	

	

}
