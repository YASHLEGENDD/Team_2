package com.employee.management.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.hrms.entity.LeaveType;
@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {

    Optional<LeaveType> findByLeaveName(String leaveName);
}
