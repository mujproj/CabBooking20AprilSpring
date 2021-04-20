package com.cabbooking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabbooking.dto.TripBookingAdd;
import com.cabbooking.dto.TripBookingReturn;
import com.cabbooking.dto.TripBookingUpdate;
import com.cabbooking.service.ITripBookingService;

@RestController
@RequestMapping("/tripBooking")
public class TripBookingController {

	@Autowired
	private ITripBookingService tripBookingService;

	@PostMapping("/addTripBooking")
	public TripBookingReturn addTripBooking(@Valid @RequestBody TripBookingAdd tripBooking) {
		return this.tripBookingService.insertTripBooking(tripBooking);
	}

	@PutMapping("/updateTripBooking/{id}")
	public TripBookingReturn updateTripBooking(@PathVariable("id") int id,
			@RequestBody TripBookingUpdate updateTripBooking) {
		updateTripBooking.setTripBookingId(id);
		return this.tripBookingService.updateTripBooking(updateTripBooking);
	}

	@DeleteMapping("/deleteTrip/{id}")
	public TripBookingReturn delete(@PathVariable("id") int id) {
		return this.tripBookingService.deleteTripBooking(id);
	}

	@GetMapping("/viewTripBookingByCustomerId/{id}")
	public List<TripBookingReturn> viewTripsByCustomerId(@PathVariable("id") int id) {
		return this.tripBookingService.viewAllTripsCustomer(id);
	}

	@GetMapping("/viewTripBill/{id}")
	public Float viewTripBill(@PathVariable("id") int id) {
		return this.tripBookingService.calculateBill(id).getBill();
	}
}
