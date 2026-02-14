 package com.employee.management.hrms.entity;

 import jakarta.persistence.*;
 import java.util.List;

 @Entity
 @Table(name = "departments")
 public class Department {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int departmentId;

     private String departmentName;

     private String description;

     @OneToMany
     private List<Employee> employees;
     
     // Getters & Setters

	 public int getDepartmentId() {
		 return departmentId;
	 }

	 public void setDepartmentId(int departmentId) {
		 this.departmentId = departmentId;
	 }

	 public String getDepartmentName() {
		 return departmentName;
	 }

	 public void setDepartmentName(String departmentName) {
		 this.departmentName = departmentName;
	 }

	 public String getDescription() {
		 return description;
	 }

	 public void setDescription(String description) {
		 this.description = description;
	 }

	 public List<Employee> getEmployees() {
		 return employees;
	 }

	 public void setEmployees(List<Employee> employees) {
		 this.employees = employees;
	 }

     
 }