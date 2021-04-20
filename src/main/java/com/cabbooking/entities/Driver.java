package com.cabbooking.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "driver4_table")
public class Driver extends AbstractUser {

	@Id
	@GeneratedValue
	private int driverId;
	private String licenseNo;

	@JsonManagedReference
	@OneToOne(targetEntity = Cab.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Cab cab;
	private float rating;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "driver")
	private List<TripBooking> tripBooking = new ArrayList<TripBooking>();

	public Driver() {
		super();
	}

	public Driver(String username, String password, String mobileNumber, String email, String licenseNo, float rating) {
		super(username, password, mobileNumber, email);
		this.licenseNo = licenseNo;
		this.rating = rating;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
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

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public List<TripBooking> getTripBooking() {
		return tripBooking;
	}

	public void setTripBooking(List<TripBooking> tripBooking) {
		this.tripBooking = tripBooking;
	}

}