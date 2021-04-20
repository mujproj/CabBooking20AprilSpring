package com.cabbooking.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cabbooking.dto.AdminAdd;
import com.cabbooking.dto.AdminReturn;
import com.cabbooking.dto.AdminUpdate;
import com.cabbooking.entities.TripBooking;

public interface IAdminService {
	public AdminReturn insertAdmin(AdminAdd addAdmin);
	public AdminReturn updateAdmin(AdminUpdate updateAdmin);
	public AdminReturn deleteAdmin(int adminId);
	public AdminReturn validateAdmin(String username, String password);
	public List<TripBooking>getAllTrips(int customerId);
	public List<TripBooking>getTripsCabwise();
	public List<TripBooking>getTripsCustomerwise();
	public List<TripBooking>getTripsDatewise();
	public List<TripBooking>getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate);
}