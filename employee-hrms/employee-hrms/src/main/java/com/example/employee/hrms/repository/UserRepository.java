package com.example.employee.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.employee.hrms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}