package com.cabbooking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabbooking.dto.AdminAdd;
import com.cabbooking.dto.AdminReturn;
import com.cabbooking.dto.AdminUpdate;
import com.cabbooking.entities.Admin;
import com.cabbooking.entities.Customer;
import com.cabbooking.entities.Driver;
import com.cabbooking.entities.TripBooking;
import com.cabbooking.exception.AdminNotFoundException;
import com.cabbooking.exception.CabNotFoundException;
import com.cabbooking.exception.CustomerNotFoundException;
import com.cabbooking.repository.IAdminRepository;
import com.cabbooking.repository.ICustomerRepository;
import com.cabbooking.repository.IDriverRepository;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Autowired
	private IAdminRepository adminRepository;
	
	@Autowired
	private IDriverRepository driverRepository;
	
	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public AdminReturn insertAdmin(AdminAdd addAdmin) {
		Admin admin = this.adminRepository.findByUsername(addAdmin.getUsername());
		Driver driver = this.driverRepository.findByUsername(addAdmin.getUsername());
		Customer customer = this.customerRepository.findByUsername(addAdmin.getUsername()) ;
		
		if(admin != null || driver != null || customer != null) {
			throw new RuntimeException("Username already exists");
		}
		
		admin = new Admin(addAdmin.getUsername(), addAdmin.getPassword(), addAdmin.getMobileNumber(), addAdmin.getEmail());
		Admin addedAdmin = this.adminRepository.save(admin);
		
		AdminReturn adminReturn = new AdminReturn(addedAdmin.getAdminId(), addedAdmin.getUsername(),
				addedAdmin.getPassword(), addedAdmin.getMobileNumber(), addedAdmin.getEmail());
		return adminReturn;
	}

	@Override
	public AdminReturn updateAdmin(AdminUpdate updateAdmin) {

		// checking if admin id entered actualy exists or not
		Optional<Admin> adminOptional = this.adminRepository.findById(updateAdmin.getAdminId());
		if (adminOptional.isEmpty()) {
			throw new AdminNotFoundException("Admin Not Found With Id " + updateAdmin.getAdminId());
		}

		// if everything is fine getting admin object
		Admin admin = adminOptional.get();

		// checking if user enters username as null, the original value should remain
		// intact
		if (updateAdmin.getUsername() == null) {
			updateAdmin.setUsername(admin.getUsername());
		}

		// if everything is fine, we will update the username to original one
		else {
			admin.setUsername(updateAdmin.getUsername());
		}

		// checking if user enters password as null, the original value should remain
		// intact
		if (updateAdmin.getPassword() == null) {
			updateAdmin.setPassword(admin.getPassword());
		}

		// if everything is fine, we will update the password to original one
		else {
			admin.setPassword(updateAdmin.getPassword());
		}

		// checking if user enters mobileNumber as null, the original value should
		// remain intact
		if (updateAdmin.getMobileNumber() == null) {
			updateAdmin.setMobileNumber(admin.getMobileNumber());
		}

		// if everything is fine, we will update the mobileNumber to original one
		else {
			admin.setMobileNumber(updateAdmin.getMobileNumber());
		}

		// checking if user enters email as null, the original value should remain
		// intact
		if (updateAdmin.getEmail() == null) {
			updateAdmin.setEmail(admin.getEmail());
		}

		// if everything is fine, we will update the email to original one
		else {
			admin.setEmail(updateAdmin.getEmail());
		}

		Admin addedAdmin = this.adminRepository.save(admin);
		AdminReturn adminReturn = new AdminReturn(addedAdmin.getAdminId(), addedAdmin.getUsername(),
				addedAdmin.getPassword(), addedAdmin.getMobileNumber(), addedAdmin.getEmail());
		return adminReturn;
	}

	@Override
	public AdminReturn deleteAdmin(int adminId) {

		// checking if admin id entered actualy exists or not
		Optional<Admin> adminOptional = this.adminRepository.findById(adminId);
		if (adminOptional.isEmpty()) {
			throw new AdminNotFoundException("Admin Not Found With Id " + adminId);
		}

		// if everything is fine getting admin object
		Admin admin = adminOptional.get();

		AdminReturn adminReturn = new AdminReturn(admin.getAdminId(), admin.getUsername(), admin.getPassword(),
				admin.getMobileNumber(), admin.getEmail());

		this.adminRepository.delete(admin);
		return adminReturn;
	}

	@Override
	public AdminReturn validateAdmin(String username, String password) {

		Admin admin = this.adminRepository.findByUsernameAndPassword(username, password);

		if (admin == null) {
			throw new AdminNotFoundException("No Admin Found with matching username and password");
		}

		AdminReturn adminReturn = new AdminReturn(admin.getAdminId(), admin.getUsername(), admin.getPassword(),
				admin.getMobileNumber(), admin.getEmail());
		return adminReturn;
	}

	@Override
	public List<TripBooking> getAllTrips(int customerId) {
		List<TripBooking> listOfTripBookingBasedOnCustomerId = this.adminRepository.getAllTrips(customerId);
		if (listOfTripBookingBasedOnCustomerId.size() <= 0) {
			throw new CustomerNotFoundException("No TripBooking Found for customer with customer id as " + customerId);
		}
		return listOfTripBookingBasedOnCustomerId;
	}

	@Override
	public List<TripBooking> getTripsCabwise() {
		List<TripBooking> listOfTripBookingCabWise = this.adminRepository.getTripsCabwise();
		if (listOfTripBookingCabWise.size() <= 0) {
			throw new CabNotFoundException("No Trips Found Cabwise");
		}
		return listOfTripBookingCabWise;
	}

	@Override
	public List<TripBooking> getTripsCustomerwise() {
		List<TripBooking> listOfTripBookingCustomerWise = this.adminRepository.getTripsCustomerwise();
		if (listOfTripBookingCustomerWise.size() <= 0) {
			throw new CustomerNotFoundException("No Trips Found CustomerWise");
		}
		return listOfTripBookingCustomerWise;
	}

	@Override
	public List<TripBooking> getTripsDatewise() {
		List<TripBooking> listOfTripBookingDateWise = this.adminRepository.getTripsDatewise();
		if (listOfTripBookingDateWise.size() <= 0) {
			throw new RuntimeException("No Trips Found");
		}
		return listOfTripBookingDateWise;
	}

	@Override
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		List<TripBooking> listOfTripBooking = this.adminRepository.getAllTripsForDays(customerId, fromDate, toDate);
		if (listOfTripBooking.size() <= 0) {
			throw new CustomerNotFoundException("No Customer trip with customerId as " + customerId);
		}
		return listOfTripBooking;
	}

//	@Override
//	public Admin insertAdmin(Admin admin) {
//		Admin checkAdmin = this.adminRepository.findByUsername(admin.getUsername());
//		if(checkAdmin != null) {
//			throw new RuntimeException("Duplicate Username");
//		}
//		Admin newAdmin = adminRepository.save(admin);
//		return newAdmin;
//	}
//
//	@Override
//	public Admin updateAdmin(Admin admin) {
//		Optional<Admin> adminOptional = this.adminRepository.findById(admin.getAdminId());
//		if (adminOptional.isEmpty()) {
//			throw new AdminNotFoundException("No Admin Found with Admin ID " + admin.getAdminId());
//		}
//		Admin newAdmin = adminRepository.save(admin);
//		return newAdmin;
//	}
//
//	@Override
//	public Admin deleteAdmin(int adminId) {
//		Optional<Admin> newAdminOptional = this.adminRepository.findById(adminId);
//		if (newAdminOptional.isEmpty()) {
//			throw new AdminNotFoundException("No Admin Found With Admin Id " + adminId);
//		}
//		this.adminRepository.deleteById(adminId);
//		return newAdminOptional.get();
//	}
//	
//	@Override
//	public Admin validateAdmin(String username, String password) {
//		Admin admin = this.adminRepository.findByUsernameAndPassword(username, password);
//		if(admin == null) {
//			throw new AdminNotFoundException("No admin found with these username and password");
//		}
//		return admin;
//	}
}
