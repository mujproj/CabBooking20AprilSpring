package com.cabbooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabbooking.dto.AdminAdd;
import com.cabbooking.dto.AdminReturn;
import com.cabbooking.dto.AdminUpdate;
import com.cabbooking.dto.LoginRequest;
import com.cabbooking.entities.TripBooking;
import com.cabbooking.service.IAdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@PostMapping("/add")
	public AdminReturn addAdmin(@Valid @RequestBody AdminAdd addAdmin) {
		return this.adminService.insertAdmin(addAdmin);
	}

	@PutMapping("/updateAdmin/{id}")
	public AdminReturn updateAdmin(@PathVariable("id") int adminId, @Valid @RequestBody AdminUpdate updateAdmin) {
		updateAdmin.setAdminId(adminId);
		return this.adminService.updateAdmin(updateAdmin);
	}

	@DeleteMapping("/deleteAdmin/{id}")
	public AdminReturn deleteAdmin(@PathVariable("id") int adminId) {
		return this.adminService.deleteAdmin(adminId);
	}

	@GetMapping("/allTripsByCustomerId/{id}")
	public List<TripBooking> getAllTrips(@PathVariable("id") int customerId) {
		return this.adminService.getAllTrips(customerId);
	}

	@GetMapping("/allTripsCabWise")
	public List<TripBooking> getAllTripsCabWise() {
		return this.adminService.getTripsCabwise();
	}

	@GetMapping("/allTripsCustomerWise")
	public List<TripBooking> getAllTripsCustomerWise() {
		return this.adminService.getTripsCustomerwise();
	}

	@GetMapping("/allTripsDateWise")
	public List<TripBooking> getAllTripsDateWise() {
		return this.adminService.getTripsDatewise();
	}
	
	@PostMapping("/validateAdmin")
	public AdminReturn validateAdmin(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
	
		List<ObjectError> err = result.getAllErrors();
		for(ObjectError er: err) {
			System.out.println(">>" + er.getDefaultMessage());
		}
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		return this.adminService.validateAdmin(username, password);
	}

}
