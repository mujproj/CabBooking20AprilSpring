package com.cabbooking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cab4_table")
public class Cab {

	@Id
	@GeneratedValue
	private int cabId;
	private String carType;
	private float perKmRate;

	@JsonBackReference
	@OneToOne(targetEntity = Driver.class, mappedBy = "cab")
	private Driver driver;

	public Cab() {
		super();
	}

	public Cab(String carType, float perKmRate) {
		super();
		this.carType = carType;
		this.perKmRate = perKmRate;
	}

	public int getCabId() {
		return cabId;
	}

	public void setCabId(int cabId) {
		this.cabId = cabId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public float getPerKmRate() {
		return perKmRate;
	}

	public void setPerKmRate(float perKmRate) {
		this.perKmRate = perKmRate;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}