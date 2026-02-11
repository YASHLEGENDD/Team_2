package com.employee.management.hrms.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.hrms.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find user by email (for login)
    Optional<User> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);
}

