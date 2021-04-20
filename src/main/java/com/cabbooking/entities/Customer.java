package com.cabbooking.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "customer4_table")
public class Customer extends AbstractUser {

	@Id
	@GeneratedValue
	private int customerId;

	@JsonBackReference
	@OneToOne(targetEntity = TripBooking.class, mappedBy = "customer", cascade = CascadeType.ALL)
	private TripBooking tripBooking;

	public Customer() {
		super();
	}

	public Customer(String username, String password, String mobileNumber, String email) {
		super(username, password, mobileNumber, email);
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public TripBooking getTripBooking() {
		return tripBooking;
	}

	public void setTripBooking(TripBooking tripBooking) {
		this.tripBooking = tripBooking;
	}

}
