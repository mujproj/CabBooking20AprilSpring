package com.cabbooking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginRequest {

	@NotBlank(message = "Username is mandatory")
	@Size(min = 2, max = 20, message = "Name Should be between 2 to 20 characters")
	private String username;

	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "start-of-string\r\n"
			+ "a digit must occur at least once\r\n" + "a lower case letter must occur at least once\r\n"
			+ "an upper case letter must occur at least once\r\n" + "a special character must occur at least once\r\n"
			+ "no whitespace allowed in the entire string\r\n" + "anything, at least eight places though end-of-string")
	private String password;

	public LoginRequest() {
		super();
	}

	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

}
