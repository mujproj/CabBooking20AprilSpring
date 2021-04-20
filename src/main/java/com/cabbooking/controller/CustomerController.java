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

import com.cabbooking.dto.CustomerAdd;
import com.cabbooking.dto.CustomerReturn;
import com.cabbooking.dto.CustomerUpdate;
import com.cabbooking.dto.LoginRequest;
import com.cabbooking.entities.TripBooking;
import com.cabbooking.repository.ICustomerRepository;
import com.cabbooking.service.ICustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ICustomerRepository customerRepository;
	
	@PostMapping("/addCustomer")
	public CustomerReturn addCustomer(@Valid @RequestBody CustomerAdd customer) {
		return this.customerService.insertCustomer(customer);
	}

	@PutMapping("/updateCustomer/{id}")
	public CustomerReturn updateCustomer(@PathVariable("id") int id, @Valid @RequestBody CustomerUpdate updateCustomer) {
		updateCustomer.setCustomerId(id);
		return this.customerService.updateCustomer(updateCustomer);
	}

	@DeleteMapping("/deleteCustomer/{id}")
	public CustomerReturn delete(@PathVariable("id") int id) {
		return this.customerService.deleteCustomer(id);
	}

	@PostMapping("/validateCustomer")
	public CustomerReturn validateCustomer(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
		}
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		return this.customerService.validateCustomer(username, password);
	}

	@GetMapping("/allCustomers")
	public List<CustomerReturn> allCustomers() {
		return this.customerService.viewCustomers();
	}

	@GetMapping("/getCustomerDetails/{id}")
	public CustomerReturn getCustomerDetails(@PathVariable("id") int id) {
		return this.customerService.viewCustomer(id);
	}
	
	@GetMapping("/getCustomerByTrips/{id}")
	public TripBooking getCustomers(@PathVariable("id") int id) {
		return this.customerRepository.findById(id).get().getTripBooking();
	}

}
