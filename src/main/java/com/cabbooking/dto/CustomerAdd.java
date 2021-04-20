package com.cabbooking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerAdd {

	@NotBlank(message = "Username is mandatory")
	@Size(min = 2, max = 20, message = "Name Should be between 2 to 20 characters")
	private String username;

	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "start-of-string\r\n"
			+ "a digit must occur at least once\r\n" + "a lower case letter must occur at least once\r\n"
			+ "an upper case letter must occur at least once\r\n" + "a special character must occur at least once\r\n"
			+ "no whitespace allowed in the entire string\r\n" + "anything, at least eight places though end-of-string")
	private String password;

	@NotBlank(message = "Phone must not be blank")
	@Size(min = 10, max = 12, message = "Number should be of 10 digits or max 12 digits")
	@Pattern(regexp = "[0-9]+", message = "Phone should be numeric")
	private String mobileNumber;

	@NotBlank(message = "Email Should not be blank")
	@Email(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Email Should be like XYZ@smart.com")
	private String email;

	public CustomerAdd() {
		super();
	}

	public CustomerAdd(String username, String password, String mobileNumber, String email) {
		super();
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
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
