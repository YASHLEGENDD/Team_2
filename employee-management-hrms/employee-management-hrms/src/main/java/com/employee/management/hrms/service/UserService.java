package com.employee.management.hrms.service;

import java.util.List;

import com.employee.management.hrms.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto dto);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	UserDto UpadateUser(Integer id, UserDto dto);
	void deleteUser(Integer id);

}
