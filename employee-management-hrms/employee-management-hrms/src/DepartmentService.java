package com.employee.management.hrms.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface DepatmentService {

		@Service
		public interface DepartmentServiceImp extends JpaRepository<DepartmentServiceImp,Integer > {
			
			
		}

	}



