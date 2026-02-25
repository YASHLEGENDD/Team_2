package com.example.employee.hrms.service.serviceImp;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.employee.hrms.entity.User;
import com.example.employee.hrms.repository.UserRepository;
import com.example.employee.hrms.service.CustomUserDetailsService;

@Service
public class CustomUserDetailsServiceImp implements CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(
                        Collections.singleton(
                                new SimpleGrantedAuthority("ROLE_" + user.getRole())
                        )
                )
                .build();
    }
}