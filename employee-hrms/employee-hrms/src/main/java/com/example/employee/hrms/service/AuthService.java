package com.example.employee.hrms.service;

import com.example.employee.hrms.DTO.LoginRequestDto;
import com.example.employee.hrms.DTO.LoginResponseDto;
import com.example.employee.hrms.DTO.RegisterRequestDto;

public interface AuthService {

    String register(RegisterRequestDto request);

    LoginResponseDto login(LoginRequestDto request);
}
