package com.employee.management.hrms.service.serviceImp;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.hrms.entity.Department;
import com.employee.management.hrms.repository.DepartmentRepository;
import com.employee.management.hrms.service.DepatmentService;

@Service
public class DepartmentServiceImp  implements DepatmentService{
	
	
	private DepartmentRepository dep;
	    
	@Autowired
	    public DepartmentServiceImp(DepartmentRepository dep) {
	    	this.dep=dep;
	    }
	
	//Inserting entity 
	 public Department adddepartmentId( Department d) {
		 return dep.save(d);
	 }
	 
	 public Department adddepartmentName(Department d) {
		 return dep.save(d);
	 }
	 
	 public Department adddescription(Department d) {
		 return dep.save(d);
	 }
	 
	 public List<Department>getAll(){
		 return dep.findAll();
	 }

	    
	}

	

	

	    