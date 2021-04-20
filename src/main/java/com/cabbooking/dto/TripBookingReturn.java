package com.cabbooking.dto;

import java.time.LocalDateTime;

import com.cabbooking.entities.Customer;
import com.cabbooking.entities.Driver;

public class TripBookingReturn {

	private int tripBookingId;
	private Customer customer;
	private Driver driver;
	private String fromLocaton;
	private String toLocation;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private boolean status;
	private float distanceInKm;
	private float bill;

	public TripBookingReturn() {
		super();
	}

	public TripBookingReturn(int tripBookingId, Customer customer, Driver driver, String fromLocaton, String toLocation,
			LocalDateTime fromDateTime, LocalDateTime toDateTime, boolean status, float distanceInKm, float bill) {
		super();
		this.tripBookingId = tripBookingId;
		this.customer = customer;
		this.driver = driver;
		this.fromLocaton = fromLocaton;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.status = status;
		this.distanceInKm = distanceInKm;
		this.bill = bill;
	}

	public int getTripBookingId() {
		return tripBookingId;
	}

	public void setTripBookingId(int tripBookingId) {
		this.tripBookingId = tripBookingId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getFromLocaton() {
		return fromLocaton;
	}

	public void setFromLocaton(String fromLocaton) {
		this.fromLocaton = fromLocaton;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public float getDistanceInKm() {
		return distanceInKm;
	}

	public void setDistanceInKm(float distanceInKm) {
		this.distanceInKm = distanceInKm;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}

}
