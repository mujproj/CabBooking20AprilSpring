package com.cabbooking.service;

import java.util.List;

import com.cabbooking.dto.TripBookingAdd;
import com.cabbooking.dto.TripBookingReturn;
import com.cabbooking.dto.TripBookingUpdate;

public interface ITripBookingService {
	public TripBookingReturn insertTripBooking(TripBookingAdd tripBookingAdd);
	public TripBookingReturn updateTripBooking(TripBookingUpdate tripBookingUpdate);
	public TripBookingReturn deleteTripBooking(int tripBookingId);
	public List<TripBookingReturn> viewAllTripsCustomer(int customerId);
	public TripBookingReturn calculateBill(int customerId);
}