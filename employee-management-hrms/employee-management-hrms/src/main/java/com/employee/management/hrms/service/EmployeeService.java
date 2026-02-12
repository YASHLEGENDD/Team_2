package com.employee.management.hrms.service;

import java.util.List;

import com.employee.management.hrms.dto.EmployeeDTO;

public interface EmployeeService {
	  EmployeeDTO createEmployee(EmployeeDTO dto);

	    EmployeeDTO getEmployeeById(Integer id);

	    List<EmployeeDTO> getAllEmployees();

	    EmployeeDTO updateEmployee(Integer id, EmployeeDTO dto);

	    void deleteEmployee(Integer id);

}
