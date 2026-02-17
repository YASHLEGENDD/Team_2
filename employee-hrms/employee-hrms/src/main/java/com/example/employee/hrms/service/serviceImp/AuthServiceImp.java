package com.example.employee.hrms.service.serviceImp;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employee.hrms.DTO.LoginRequestDto;
import com.example.employee.hrms.DTO.LoginResponseDto;
import com.example.employee.hrms.DTO.RegisterRequestDto;
import com.example.employee.hrms.config.JwtUtil;
import com.example.employee.hrms.entity.User;
import com.example.employee.hrms.repository.UserRepository;
import com.example.employee.hrms.service.AuthService;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImp(UserRepository userRepository,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTER
    @Override
    public String register(RegisterRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(
                request.getRole() != null && !request.getRole().isBlank()
                        ? request.getRole()
                        : "USER"
        );
        user.setAccountStatus("ACTIVE");

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN
    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
        );

        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expiresAt = issuedAt.plusHours(5);

        return new LoginResponseDto(
                token,
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                issuedAt,
                expiresAt
        );
    }
}
