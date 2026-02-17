package com.example.employee.hrms.service.serviceImp;

import com.example.employee.hrms.entity.User;
import com.example.employee.hrms.repository.UserRepository;
import com.example.employee.hrms.service.CustomUserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsServiceImp implements CustomUserDetailsService {

    private final UserRepository userRepository;

    // Manual constructor (NO Lombok)
    public CustomUserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(
                        new SimpleGrantedAuthority("ROLE_" + user.getRole())
                )
        );
    }
}
