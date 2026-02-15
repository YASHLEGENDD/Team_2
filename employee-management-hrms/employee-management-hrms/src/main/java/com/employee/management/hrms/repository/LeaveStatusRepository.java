package com.employee.management.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employee.management.hrms.entity.LeaveRequest;
@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveRequest, Integer >{


}
