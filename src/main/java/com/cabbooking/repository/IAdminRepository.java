package com.cabbooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cabbooking.entities.Admin;
import com.cabbooking.entities.TripBooking;
import com.cabbooking.exception.CustomerNotFoundException;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {
//	public Admin insertAdmin(Admin admin);
//	public Admin updateAdmin(Admin admin) throws AdminNotFoundException;
//	public Admin deleteAdmin(int adminId) throws AdminNotFoundException;

	public Admin findByUsername(String username);

	public Admin findByUsernameAndPassword(String username, String password);

	@Query("select trp from TripBooking trp where trp.customer.customerId =:customerId")
	public List<TripBooking> getAllTrips(@Param("customerId") int customerId) throws CustomerNotFoundException;

	@Query("select trp from TripBooking trp where trp.driver.cab.cabId in (select c.cabId from Cab c group by c.cabId )")
	public List<TripBooking> getTripsCabwise();

	@Query("Select trp from TripBooking trp where trp.customer.customerId in (select c.customerId from Customer c group by c.customerId)")
	public List<TripBooking> getTripsCustomerwise();

	@Query("select trp from TripBooking trp where trp.fromDateTime in (select tr.fromDateTime from TripBooking tr group by tr.fromDateTime)")
	public List<TripBooking> getTripsDatewise();

	@Query("select trp from TripBooking trp where trp.customer.customerId =:customerId and trp.fromDateTime =:fromDate and trp.toDateTime =:toDate")
	public List<TripBooking> getAllTripsForDays(@Param("customerId") int customerId,
			@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate)
			throws CustomerNotFoundException;
}