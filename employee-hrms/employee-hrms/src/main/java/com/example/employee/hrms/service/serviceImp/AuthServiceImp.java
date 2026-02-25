package com.example.employee.hrms.service.serviceImp;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employee.hrms.DTO.LoginRequestDto;
import com.example.employee.hrms.DTO.LoginResponseDto;
import com.example.employee.hrms.DTO.RegisterRequestDto;
import com.example.employee.hrms.config.JwtUtil;
import com.example.employee.hrms.entity.User;
import com.example.employee.hrms.repository.UserRepository;
import com.example.employee.hrms.service.AuthService;
import com.example.employee.hrms.service.CustomUserDetailsService;

@Service
public class AuthServiceImp implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImp(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtUtil jwtUtil,
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
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
        user.setRole(request.getRole()); // ADMIN / EMPLOYEE / MANAGER

        userRepository.save(user);

        return "User registered successfully";
    }

    //  LOGIN 
    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Load user details (this now gives ROLE_ prefixed authority)
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getEmail());

        // Generate token
        String token = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // IMPORTANT: Return role with ROLE_ prefix
        return new LoginResponseDto(
                token,
                user.getEmail(),
                "ROLE_" + user.getRole()
        );
    }
}