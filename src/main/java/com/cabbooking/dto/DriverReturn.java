package com.cabbooking.dto;

import com.cabbooking.entities.Cab;

public class DriverReturn {

	private String username;
	private String password;
	private String mobileNumber;
	private String email;
	private String licenseNo;
	private Cab cab;
	private int driverId;
	private float rating;

	public DriverReturn() {
		super();
	}

	public DriverReturn(String username, String password, String mobileNumber, String email, String licenseNo, Cab cab,
			int driverId, float rating) {
		super();
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.licenseNo = licenseNo;
		this.cab = cab;
		this.driverId = driverId;
		this.rating = rating;
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

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
