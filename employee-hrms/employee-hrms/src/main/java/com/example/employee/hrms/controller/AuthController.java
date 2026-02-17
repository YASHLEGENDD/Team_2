package com.example.employee.hrms.controller;

import com.example.employee.hrms.DTO.LoginRequestDto;
import com.example.employee.hrms.DTO.LoginResponseDto;
import com.example.employee.hrms.DTO.RegisterRequestDto;
import com.example.employee.hrms.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequestDto request) {

        String response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequest) {

        LoginResponseDto response = authService.login(loginRequest);

        return ResponseEntity.ok(response);
    }
}
