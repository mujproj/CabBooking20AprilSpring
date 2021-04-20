package com.cabbooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabbooking.dto.TripBookingAdd;
import com.cabbooking.dto.TripBookingReturn;
import com.cabbooking.dto.TripBookingUpdate;
import com.cabbooking.entities.Customer;
import com.cabbooking.entities.Driver;
import com.cabbooking.entities.TripBooking;
import com.cabbooking.exception.CustomerNotFoundException;
import com.cabbooking.exception.DriverNotFoundException;
import com.cabbooking.repository.ICustomerRepository;
import com.cabbooking.repository.IDriverRepository;
import com.cabbooking.repository.ITripBookingRepository;

@Service
@Transactional
public class TripBookingService implements ITripBookingService {

	@Autowired
	private ITripBookingRepository tripBookingRepository;

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IDriverRepository driverRepository;

	@Override
	public TripBookingReturn insertTripBooking(TripBookingAdd tripBookingAdd) {

		// checking if customerId entered is valid
		int customerId = tripBookingAdd.getCustomerId();
		Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException("No Customer Actually exists with customer Id " + customerId);
		}

		// since it is valid we are taking out the customer object
		Customer customer = optionalCustomer.get();

		// checking if driverId entered is valid
		int driverId = tripBookingAdd.getDriverId();
		Optional<Driver> optionalDriver = this.driverRepository.findById(driverId);
		if (optionalDriver.isEmpty()) {
			throw new DriverNotFoundException("No Driver Actually exists with driver id " + driverId);
		}

		// since it is valid we are taking out the Driver object
		Driver driver = optionalDriver.get();

		// Taking out the tripbooking details
		TripBooking tripBooking = new TripBooking(tripBookingAdd.getFromLocation(), tripBookingAdd.getToLocation(),
				tripBookingAdd.getFromDateTime(), tripBookingAdd.getToDateTime(), true,
				tripBookingAdd.getDistanceInKm(), tripBookingAdd.getBill());

		// Synchronising tripBooking with customer and driver
		tripBooking.setCustomerId(customer);
		tripBooking.setDriver(driver);

		// Synchronizing driver and customer with tripBooking
		customer.setTripBooking(tripBooking);
		List<TripBooking> tripBookings = driver.getTripBooking();
		tripBookings.add(tripBooking);
		driver.setTripBooking(tripBookings);

		TripBooking addedTripBooking = this.tripBookingRepository.save(tripBooking);

		TripBookingReturn tripBookingReturn = new TripBookingReturn(addedTripBooking.getTripBookingId(),
				addedTripBooking.getCustomerId(), addedTripBooking.getDriver(), addedTripBooking.getFromLocation(),
				addedTripBooking.getToLocation(), addedTripBooking.getFromDateTime(), addedTripBooking.getToDateTime(),
				addedTripBooking.isStatus(), addedTripBooking.getDistanceInKm(), addedTripBooking.getBill());
		return tripBookingReturn;
	}

	@Override
	public TripBookingReturn updateTripBooking(TripBookingUpdate tripBookingUpdate) {

		// checking if tripBookingId entered is valid
		int tripBookingId = tripBookingUpdate.getTripBookingId();
		Optional<TripBooking> tripBookingOptional = this.tripBookingRepository.findById(tripBookingId);
		if (tripBookingOptional.isEmpty()) {
			throw new RuntimeException("No TripBooking Exists with trip booking id " + tripBookingId);
		}

		// getting the tripBooking object out if everything is fine
		TripBooking tripBooking = tripBookingOptional.get();

		// if user does not enters driverId , it will be taken as 0, hence updating it
		// to one which it originally exists
		if (tripBookingUpdate.getDriverId() == 0) {
			tripBookingUpdate.setDriverId(tripBooking.getDriver().getDriverId());
		}

		// if user does not enteres customerId, it will be taken as 0, hence updating it
		// to one which it originally exists
		if (tripBookingUpdate.getCustomerId() == 0) {
			tripBookingUpdate.setCustomerId(tripBooking.getCustomerId().getCustomerId());
		}

		// checking if customer Id entered is valid
		int customerId = tripBookingUpdate.getCustomerId();
		Optional<Customer> customerOptional = this.customerRepository.findById(customerId);
		if (customerOptional.isEmpty()) {
			throw new CustomerNotFoundException("No Customer Exists with customer id " + customerId);
		}

		// getting the customer object out if everything is fine
		Customer customer = customerOptional.get();

		// checking if driverId entered is valid
		int driverId = tripBookingUpdate.getDriverId();
		Optional<Driver> driverOptional = this.driverRepository.findById(driverId);
		if (driverOptional.isEmpty()) {
			throw new DriverNotFoundException("No Driver Exists with driver id " + driverId);
		}

		// getting the driver object out if everything is fine
		Driver driver = driverOptional.get();

		// removing tripBooking from driver to insert incase of update
		List<TripBooking> listOfTripsOfDriver = driver.getTripBooking();
		listOfTripsOfDriver.remove(tripBooking);

		// if user does not enters fromLocation, it will be taken as null, so updating
		// it to one which it originally exists
		if (tripBookingUpdate.getFromLocation() == null) {
			tripBookingUpdate.setFromLocation(tripBooking.getFromLocation());
		}

		// else updating the tripBooking
		else {
			tripBooking.setFromLocation(tripBookingUpdate.getFromLocation());
		}

		// if user does not enters toLocation, it will be taken as null, so updating it
		// to one which it originally exists
		if (tripBookingUpdate.getToLocation() == null) {
			tripBookingUpdate.setToLocation(tripBooking.getToLocation());
		}

		// else updating the tripBooking
		else {
			tripBooking.setToLocation(tripBookingUpdate.getToLocation());
		}

		// if user does not enters fromDateTime, it will be taken as null, so updating
		// it to one which it originally exists
		if (tripBookingUpdate.getFromDateTime() == null) {
			tripBookingUpdate.setFromDateTime(tripBooking.getFromDateTime());
		}

		// else updating the tripBooking
		else {
			tripBooking.setFromDateTime(tripBookingUpdate.getFromDateTime());
		}

		// if user does not enters toDateTime, it will be taken as null, so updating it
		// to one which it originally exists
		if (tripBookingUpdate.getToDateTime() == null) {
			tripBookingUpdate.setToDateTime(tripBooking.getToDateTime());
		}

		// else updating the tripBooking
		else {
			tripBooking.setToDateTime(tripBookingUpdate.getToDateTime());
		}

		// if user does not enters bill it will taken as 0, so updating it to one which
		// it originally exists
		if (tripBookingUpdate.getBill() == 0) {
			tripBookingUpdate.setBill(tripBooking.getBill());
		}

		// else updating the tripBooking
		else {
			tripBooking.setBill(tripBookingUpdate.getBill());
		}

		// if user does not enters distanceInKm it will taken as 0, so updating it to
		// one which it originally exists
		if (tripBookingUpdate.getDistanceInKm() == 0) {
			tripBookingUpdate.setDistanceInKm(tripBooking.getDistanceInKm());
		}

		// else updating the tripBooking
		else {
			tripBooking.setDistanceInKm(tripBookingUpdate.getDistanceInKm());
		}

		customer.setTripBooking(tripBooking);
		listOfTripsOfDriver.add(tripBooking);
		driver.setTripBooking(listOfTripsOfDriver);
		tripBooking.setDriver(driver);
		tripBooking.setCustomerId(customer);

		TripBooking addedTripBooking = this.tripBookingRepository.save(tripBooking);
		TripBookingReturn tripBookingReturn = new TripBookingReturn(addedTripBooking.getTripBookingId(),
				addedTripBooking.getCustomerId(), addedTripBooking.getDriver(), addedTripBooking.getFromLocation(),
				addedTripBooking.getToLocation(), addedTripBooking.getFromDateTime(), addedTripBooking.getToDateTime(),
				addedTripBooking.isStatus(), addedTripBooking.getDistanceInKm(), addedTripBooking.getBill());
		return tripBookingReturn;
	}

	@Override
	public TripBookingReturn deleteTripBooking(int tripBookingId) {
		Optional<TripBooking> tripBookingOptional = this.tripBookingRepository.findById(tripBookingId);
		if (tripBookingOptional.isEmpty()) {
			throw new RuntimeException("No TripBooking with matching trip id");
		}
		TripBooking tripBooking = tripBookingOptional.get();
		Customer customer = tripBooking.getCustomerId();
		customer.setTripBooking(null);
		Driver driver = tripBooking.getDriver();
		driver.getTripBooking().remove(tripBooking);
		tripBooking.setCustomerId(null);
		tripBooking.setDriver(null);
		
		TripBookingReturn tripBookingReturn = new TripBookingReturn(tripBooking.getTripBookingId(),
				tripBooking.getCustomerId(), tripBooking.getDriver(), tripBooking.getFromLocation(),
				tripBooking.getToLocation(), tripBooking.getFromDateTime(), tripBooking.getToDateTime(),
				tripBooking.isStatus(), tripBooking.getDistanceInKm(), tripBooking.getBill());

		this.tripBookingRepository.delete(tripBooking);

		return tripBookingReturn;
	}

	@Override
	public List<TripBookingReturn> viewAllTripsCustomer(int customerId) {
		List<TripBooking> listOfTripBookingsByCustomerId = this.tripBookingRepository.viewAllTripsCustomer(customerId);
		if(listOfTripBookingsByCustomerId.size() <= 0) {
			throw new CustomerNotFoundException("No Customer TripBooking Found With Matcing customer Id");
		}
		List<TripBookingReturn> tripBookingReturn = new ArrayList<TripBookingReturn>();
		for(TripBooking tripBooking: listOfTripBookingsByCustomerId) {
			TripBookingReturn tripreturn = new TripBookingReturn(tripBooking.getTripBookingId(),
					tripBooking.getCustomerId(), tripBooking.getDriver(), tripBooking.getFromLocation(),
					tripBooking.getToLocation(), tripBooking.getFromDateTime(), tripBooking.getToDateTime(),
					tripBooking.isStatus(), tripBooking.getDistanceInKm(), tripBooking.getBill());
			tripBookingReturn.add(tripreturn);
		}
		return tripBookingReturn;
	}

	@Override
	public TripBookingReturn calculateBill(int customerId) {

		Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException("No customer found with matching customer id");
		}
		Customer customer = optionalCustomer.get();
		TripBooking tripBooking = customer.getTripBooking();
		if (tripBooking == null) {
			throw new CustomerNotFoundException("Customer Does not have matching TripBooking");
		}
		tripBooking = this.tripBookingRepository.findBillByCustomer(customer);
		TripBookingReturn tripBookingReturn = new TripBookingReturn(tripBooking.getTripBookingId(),
				tripBooking.getCustomerId(), tripBooking.getDriver(), tripBooking.getFromLocation(),
				tripBooking.getToLocation(), tripBooking.getFromDateTime(), tripBooking.getToDateTime(),
				tripBooking.isStatus(), tripBooking.getDistanceInKm(), tripBooking.getBill());
		return tripBookingReturn;
	}

}
