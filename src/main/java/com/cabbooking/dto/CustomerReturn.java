package com.cabbooking.dto;

public class CustomerReturn {

	private int customerId;
	private String username;
	private String password;
	private String mobileNumber;
	private String email;

	public CustomerReturn() {
		super();
	}

	public CustomerReturn(int customerId, String username, String password, String mobileNumber, String email) {
		super();
		this.customerId = customerId;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
