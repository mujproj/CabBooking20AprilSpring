package com.cabbooking.dto;

import java.time.LocalDateTime;

public class TripBookingUpdate {

	private int customerId;
	private int driverId;
	private String fromLocation;
	private String toLocation;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private float distanceInKm;
	private float bill;
	private int tripBookingId;

	public TripBookingUpdate() {
		super();
	}

	public TripBookingUpdate(int customerId, int driverId, String fromLocation, String toLocation,
			LocalDateTime fromDateTime, LocalDateTime toDateTime, float distanceInKm, float bill, int tripBookingId) {
		super();
		this.customerId = customerId;
		this.driverId = driverId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.distanceInKm = distanceInKm;
		this.bill = bill;
		this.tripBookingId = tripBookingId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
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

	public int getTripBookingId() {
		return tripBookingId;
	}

	public void setTripBookingId(int tripBookingId) {
		this.tripBookingId = tripBookingId;
	}

}
