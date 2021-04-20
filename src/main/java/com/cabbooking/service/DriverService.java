package com.cabbooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabbooking.dto.DriverAdd;
import com.cabbooking.dto.DriverReturn;
import com.cabbooking.dto.DriverUpdate;
import com.cabbooking.entities.Admin;
import com.cabbooking.entities.Cab;
import com.cabbooking.entities.Customer;
import com.cabbooking.entities.Driver;
import com.cabbooking.entities.TripBooking;
import com.cabbooking.exception.DriverNotFoundException;
import com.cabbooking.repository.IAdminRepository;
import com.cabbooking.repository.ICustomerRepository;
import com.cabbooking.repository.IDriverRepository;

@Service
@Transactional
public class DriverService implements IDriverService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IAdminRepository adminRepository;

	@Autowired
	private IDriverRepository driverRepository;

	@Override
	public DriverReturn insertDriver(DriverAdd addDriver) {
		Admin admin = this.adminRepository.findByUsername(addDriver.getUsername());
		Driver driver = this.driverRepository.findByUsername(addDriver.getUsername());
		Customer customer = this.customerRepository.findByUsername(addDriver.getUsername());

		if (admin != null || driver != null || customer != null) {
			throw new RuntimeException("Username already exists");
		}
		driver = new Driver(addDriver.getUsername(), addDriver.getPassword(), addDriver.getMobileNumber(),
				addDriver.getEmail(), addDriver.getLicenseNo(), 0);
		Cab cab = new Cab(addDriver.getCarType(), 45);
		cab.setDriver(driver);
		driver.setCab(cab);
		Driver addedDriver = this.driverRepository.save(driver);
		DriverReturn driverReturn = new DriverReturn(addedDriver.getUsername(), addedDriver.getPassword(),
				addedDriver.getMobileNumber(), addedDriver.getEmail(), addedDriver.getLicenseNo(), addedDriver.getCab(),
				addedDriver.getDriverId(), addedDriver.getRating());
		return driverReturn;
	}

	@Override
	public DriverReturn updateDriver(DriverUpdate updateDriver) {

		// checking if driver enters valid driver id
		Optional<Driver> driverOptional = this.driverRepository.findById(updateDriver.getDriverId());
		if (driverOptional.isEmpty()) {
			throw new DriverNotFoundException("No Driver Found with id " + updateDriver.getDriverId());
		}

		// extracting the driver object if everything is fine
		Driver driver = driverOptional.get();

		// checking if user enters username as null, the original value should remain
		// intact
		if (updateDriver.getUsername() == null) {
			updateDriver.setUsername(driver.getUsername());
		}

		// if everything is fine, we will update the username to original one
		else {
			driver.setUsername(updateDriver.getUsername());
		}

		// checking if user enters password as null, the original value should remain
		// intact
		if (updateDriver.getPassword() == null) {
			updateDriver.setPassword(driver.getPassword());
		}

		// if everything is fine, we will update the password to original one
		else {
			driver.setPassword(updateDriver.getPassword());
		}

		// checking if user enters mobileNumber as null, the original value should
		// remain intact
		if (updateDriver.getMobileNumber() == null) {
			updateDriver.setMobileNumber(driver.getMobileNumber());
		}

		// if everything is fine, we will update the mobileNumber to original one
		else {
			driver.setMobileNumber(updateDriver.getMobileNumber());
		}

		// checking if user enters email as null, the original value should remain
		// intact
		if (updateDriver.getEmail() == null) {
			updateDriver.setEmail(driver.getEmail());
		}

		// if everything is fine, we will update the email to original one
		else {
			driver.setEmail(updateDriver.getEmail());
		}

		// checking if user enters mobileNumber as null, the original value should
		// remain intact
		if (updateDriver.getLicenseNo() == null) {
			updateDriver.setLicenseNo(driver.getLicenseNo());
		}

		// if everything is fine, we will update the licenseNo to original one
		else {
			driver.setLicenseNo(updateDriver.getLicenseNo());
		}

		// extracting the cab object
		Cab cab = driver.getCab();

		// checking if user enters carType as null, original value should remain intact
		if (updateDriver.getCarType() == null) {
			updateDriver.setCarType(cab.getCarType());
		}

		// if everything is alright, we will update cab object
		else {
			cab.setCarType(updateDriver.getCarType());
		}

		//	checking if user enters rating as null, original value should remain intact
		if(updateDriver.getRating() == 0) {
			updateDriver.setRating(driver.getRating());
		}
		
		//	if everything is alright, we will update rating
		else {
			driver.setRating(updateDriver.getRating());
		}

		// setting the driver to cab, and cab to driver
		driver.setCab(cab);
		cab.setDriver(driver);

		// adding the updated driver back
		Driver addedDriver = this.driverRepository.save(driver);

		DriverReturn driverReturn = new DriverReturn(addedDriver.getUsername(), addedDriver.getPassword(),
				addedDriver.getMobileNumber(), addedDriver.getEmail(), addedDriver.getLicenseNo(), addedDriver.getCab(),
				addedDriver.getDriverId(), addedDriver.getRating());
		return driverReturn;
	}

	@Override
	public DriverReturn deleteDriver(int driverId) {

		// checking if Driver id is valid
		Optional<Driver> driverOptional = this.driverRepository.findById(driverId);
		if (driverOptional.isEmpty()) {
			throw new DriverNotFoundException("No Driver with driver id " + driverId + " exists");
		}

		// extracting the driver object
		Driver driver = driverOptional.get();

		// extracting the tripBooking
		List<TripBooking> listOfTripBooking = driver.getTripBooking();
		if (listOfTripBooking.size() > 0) {
			for (TripBooking tripBooking : listOfTripBooking) {
				tripBooking.setDriver(null);
			}
			driver.setTripBooking(null);
		}

		DriverReturn driverReturn = new DriverReturn(driver.getUsername(), driver.getPassword(),
				driver.getMobileNumber(), driver.getEmail(), driver.getLicenseNo(), driver.getCab(),
				driver.getDriverId(), driver.getRating());

		// deleting driver, hence deleting its associated cab also
		this.driverRepository.delete(driver);

		return driverReturn;
	}

	@Override
	public List<DriverReturn> viewBestDrivers() {
		List<Driver> bestDrivers = this.driverRepository.viewBestDrivers();
		if (bestDrivers.size() <= 0) {
			throw new DriverNotFoundException("No Best Driver Found");
		}
		List<DriverReturn> bestDriversReturn = new ArrayList<DriverReturn>();
		for (Driver driver : bestDrivers) {
			DriverReturn driverReturn = new DriverReturn(driver.getUsername(), driver.getPassword(),
					driver.getMobileNumber(), driver.getEmail(), driver.getLicenseNo(), driver.getCab(),
					driver.getDriverId(), driver.getRating());
			bestDriversReturn.add(driverReturn);
		}
		return bestDriversReturn;
	}

	@Override
	public DriverReturn viewDriver(int driverId) {

		// checking if Driver id is valid
		Optional<Driver> driverOptional = this.driverRepository.findById(driverId);
		if (driverOptional.isEmpty()) {
			throw new DriverNotFoundException("No Driver with driver id " + driverId + " exists");
		}

		// extracting the driver object
		Driver driver = driverOptional.get();

		DriverReturn driverReturn = new DriverReturn(driver.getUsername(), driver.getPassword(),
				driver.getMobileNumber(), driver.getEmail(), driver.getLicenseNo(), driver.getCab(),
				driver.getDriverId(), driver.getRating());
		return driverReturn;
	}

	@Override
	public DriverReturn validateDriver(String username, String password) {

		Driver driver = this.driverRepository.findByUsernameAndPassword(username, password);
		if (driver == null) {
			throw new DriverNotFoundException("No Driver found with matching credentials");
		}

		DriverReturn driverReturn = new DriverReturn(driver.getUsername(), driver.getPassword(),
				driver.getMobileNumber(), driver.getEmail(), driver.getLicenseNo(), driver.getCab(),
				driver.getDriverId(), driver.getRating());
		return driverReturn;
	}

//	@Override
//	public Driver insertDriver(Driver driver) {
//		Driver driver1 = this.driverRepository.findByUsername(driver.getUsername());
//		if(driver1 != null) {
//			throw new RuntimeException("Trying to insert duplicate driver");
//		}
//		return driverRepository.save(driver);
//	}
//
//	@Override
//	public Driver updateDriver(Driver driver) {
//		Optional<Driver> optionalDriver = this.driverRepository.findById(driver.getDriverId());
//		if (optionalDriver.isEmpty()) {
//			throw new DriverNotFoundException("No Driver Found With Driver Id " + driver.getDriverId());
//		}
//		return this.driverRepository.save(driver);
//	}
//
//	@Override
//	public Driver deleteDriver(int driverId) {
//		boolean checkIfExists = this.driverRepository.existsById(driverId);
//		if (!checkIfExists) {
//			throw new DriverNotFoundException("No Driver Found With Driver Id " + driverId);
//		}
//		Driver driver = this.driverRepository.findById(driverId).get();
//		this.driverRepository.deleteById(driverId);
//		return driver;
//	}
//
//	@Override
//	public List<Driver> viewBestDrivers() {
//		List<Driver> bestDrivers = this.driverRepository.viewBestDrivers();
//		if (bestDrivers.size() <= 0) {
//			throw new DriverNotFoundException("No Drivers Here with best rating");
//		}
//		return bestDrivers;
//	}
//
//	@Override
//	public Driver viewDriver(int driverId) {
//		boolean checkIfExists = this.driverRepository.existsById(driverId);
//		if (!checkIfExists) {
//			throw new DriverNotFoundException("No Driver Found With Driver Id " + driverId);
//		}
//		Driver driver = this.driverRepository.findById(driverId).get();
//		return driver;
//	}
//
//	@Override
//	public Driver validateDriver(String username, String password) {
//		Driver driver = this.driverRepository.findByUsernameAndPassword(username, password);
//		if(driver == null) {
//			throw new DriverNotFoundException("No Driver Found with matching username and password");
//		}
//		return driver;
//	}

}
