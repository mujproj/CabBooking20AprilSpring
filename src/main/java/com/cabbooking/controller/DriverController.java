package com.cabbooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabbooking.dto.DriverAdd;
import com.cabbooking.dto.DriverReturn;
import com.cabbooking.dto.DriverUpdate;
import com.cabbooking.dto.LoginRequest;
import com.cabbooking.entities.TripBooking;
import com.cabbooking.repository.IDriverRepository;
import com.cabbooking.service.IDriverService;

@RestController
@RequestMapping("/driver")
@CrossOrigin("*")
public class DriverController {

	@Autowired
	private IDriverService driverService;
	
	@Autowired
	private IDriverRepository driverRepository;

	@PostMapping("/addDriver")
	public DriverReturn addDriver(@Valid @RequestBody DriverAdd driver) {
		return this.driverService.insertDriver(driver);
	}

	@PutMapping("/updateDriver/{id}")
	public DriverReturn updateDriver(@PathVariable("id") int id, @Valid @RequestBody DriverUpdate updateDriver) {
		updateDriver.setDriverId(id);
		return this.driverService.updateDriver(updateDriver);
	}

	@DeleteMapping("/delete/{id}")
	public DriverReturn delete(@PathVariable("id") int id) {
		return this.driverService.deleteDriver(id);
	}

	@GetMapping("/bestDrivers")
	public List<DriverReturn> bestDrivers() {
		return this.driverService.viewBestDrivers();
	}

	@PostMapping("/validateDriver")
	public DriverReturn validateDriver(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
		}
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		return this.driverService.validateDriver(username, password);
	}

	@GetMapping("/viewDriver/{id}")
	public DriverReturn viewDriver(@PathVariable("id") int id) {
		return this.driverService.viewDriver(id);
	}
	
	@GetMapping("/getTrips/{id}")
	public List<TripBooking> viewTrips(@PathVariable("id") int id) {
		return this.driverRepository.findById(id).get().getTripBooking();
	}
}
