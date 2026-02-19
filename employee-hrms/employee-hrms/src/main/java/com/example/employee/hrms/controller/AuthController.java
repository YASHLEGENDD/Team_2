package com.example.employee.hrms.controller;

import com.example.employee.hrms.DTO.LoginRequestDto;
import com.example.employee.hrms.DTO.LoginResponseDto;
import com.example.employee.hrms.DTO.RegisterRequestDto;
import com.example.employee.hrms.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = {
                "http://127.0.0.1:5500",
                "http://localhost:5500"
        },
        allowCredentials = "true"
)
public class AuthController {

    private final AuthService authService;

    // Constructor injection (correct)
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ===================== REGISTER =====================
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequestDto request) {

        String message = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    // ===================== LOGIN =====================
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequest) {

        LoginResponseDto response = authService.login(loginRequest);

        return ResponseEntity.ok(response);
    }
}
