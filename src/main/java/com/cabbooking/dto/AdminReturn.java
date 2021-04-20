package com.cabbooking.dto;

public class AdminReturn {

	private int adminId;
	private String username;
	private String password;
	private String mobileNumber;
	private String email;

	public AdminReturn() {
		super();
	}

	public AdminReturn(int adminId, String username, String password, String mobileNumber, String email) {
		super();
		this.adminId = adminId;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
