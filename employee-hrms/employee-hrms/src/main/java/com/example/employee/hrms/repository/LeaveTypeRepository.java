package com.example.employee.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.hrms.entity.LeaveType;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

    Optional<LeaveType> findByLeaveName(String leaveName);

    boolean existsByLeaveName(String leaveName);
}

