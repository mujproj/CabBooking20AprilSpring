package com.cabbooking.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class TripBookingAdd {

	private int customerId;
	private int driverId;
	
	@NotBlank(message = "From Location cannot be left blank")
	private String fromLocation;
	
	@NotBlank(message = "To Location cannot be left blank")
	private String toLocation;

	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	
	@Min(1)
	private float distanceInKm;
	
	@Min(1)
	private float bill;

	public TripBookingAdd() {
		super();
	}

	public TripBookingAdd(int customerId, int driverId, String fromLocation, String toLocation,
			LocalDateTime fromDateTime, LocalDateTime toDateTime, float distanceInKm, float bill) {
		super();
		this.customerId = customerId;
		this.driverId = driverId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.distanceInKm = distanceInKm;
		this.bill = bill;
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

}
