package com.employee.management.hrms.dto;

public class UserDto {
	  private int userId;
	    private String fullName;
	    private String email;
	    private String password;
	    private String role;
	    private String accountStatus;
	  

	    // Default Constructor
	    public UserDto() {
	    }

	    // Parameterized Constructor
	    public UserDto(int userId, String fullName, String email,String role, String accountStatus) {
	        this.userId = userId;
	        this.fullName = fullName;
	        this.email = email;
	        this.role = role;
	        this.accountStatus = accountStatus;
	        
	    }

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getAccountStatus() {
			return accountStatus;
		}

		public void setAccountStatus(String accountStatus) {
			this.accountStatus = accountStatus;
		}

	   

}
