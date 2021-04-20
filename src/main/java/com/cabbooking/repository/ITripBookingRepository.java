package com.cabbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cabbooking.entities.Customer;
import com.cabbooking.entities.TripBooking;

public interface ITripBookingRepository extends JpaRepository<TripBooking, Integer> {
//	public TripBooking insertTripBooking(TripBooking tripBooking);
//	public TripBooking updateTripBooking(TripBooking tripBooking);
//	public TripBooking deleteTripBooking(TripBooking tripBooking);

	@Query("select trp from TripBooking trp where trp.customer.customerId =:customerId")
	public List<TripBooking> viewAllTripsCustomer(@Param("customerId") int customerId);

	public TripBooking findBillByCustomer(Customer customer);
}