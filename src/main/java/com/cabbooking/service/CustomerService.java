package com.cabbooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabbooking.dto.CustomerAdd;
import com.cabbooking.dto.CustomerReturn;
import com.cabbooking.dto.CustomerUpdate;
import com.cabbooking.entities.Admin;
import com.cabbooking.entities.Customer;
import com.cabbooking.entities.Driver;
import com.cabbooking.entities.TripBooking;
import com.cabbooking.exception.CustomerNotFoundException;
import com.cabbooking.repository.IAdminRepository;
import com.cabbooking.repository.ICustomerRepository;
import com.cabbooking.repository.IDriverRepository;

@Service
@Transactional
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IAdminRepository adminRepository;

	@Autowired
	private IDriverRepository driverRepository;

	@Override
	public CustomerReturn insertCustomer(CustomerAdd addCustomer) {
		Admin admin = this.adminRepository.findByUsername(addCustomer.getUsername());
		Driver driver = this.driverRepository.findByUsername(addCustomer.getUsername());
		Customer customer = this.customerRepository.findByUsername(addCustomer.getUsername());

		if (admin != null || driver != null || customer != null) {
			throw new RuntimeException("Username already exists");
		}

		customer = new Customer(addCustomer.getUsername(), addCustomer.getPassword(), addCustomer.getMobileNumber(),
				addCustomer.getEmail());
		Customer addedCustomer = this.customerRepository.save(customer);

		CustomerReturn customerReturn = new CustomerReturn(addedCustomer.getCustomerId(), addedCustomer.getUsername(),
				addedCustomer.getPassword(), addedCustomer.getMobileNumber(), addedCustomer.getEmail());
		return customerReturn;
	}

	@Override
	public CustomerReturn updateCustomer(CustomerUpdate updateCustomer) {

		// checking if customer enters valid customer id
		Optional<Customer> optionalCustomer = this.customerRepository.findById(updateCustomer.getCustomerId());
		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException("No Customer Foudn With Id " + updateCustomer.getCustomerId());
		}

		// extracting the customer object
		Customer customer = optionalCustomer.get();

		// checking if user enters username as null, the original value should remain
		// intact
		if (updateCustomer.getUsername() == null) {
			updateCustomer.setUsername(customer.getUsername());
		}

		// if everything is fine, we will update the username to original one
		else {
			customer.setUsername(updateCustomer.getUsername());
		}

		// checking if user enters password as null, the original value should remain
		// intact
		if (updateCustomer.getPassword() == null) {
			updateCustomer.setPassword(customer.getPassword());
		}

		// if everything is fine, we will update the password to original one
		else {
			customer.setPassword(updateCustomer.getPassword());
		}

		// checking if user enters mobileNumber as null, the original value should
		// remain intact
		if (updateCustomer.getMobileNumber() == null) {
			updateCustomer.setMobileNumber(customer.getMobileNumber());
		}

		// if everything is fine, we will update the mobileNumber to original one
		else {
			customer.setMobileNumber(updateCustomer.getMobileNumber());
		}

		// checking if user enters email as null, the original value should remain
		// intact
		if (updateCustomer.getEmail() == null) {
			updateCustomer.setEmail(customer.getEmail());
		}

		// if everything is fine, we will update the email to original one
		else {
			customer.setEmail(updateCustomer.getEmail());
		}

		Customer addedCustomer = this.customerRepository.save(customer);

		CustomerReturn customerReturn = new CustomerReturn(addedCustomer.getCustomerId(), addedCustomer.getUsername(),
				addedCustomer.getPassword(), addedCustomer.getMobileNumber(), addedCustomer.getEmail());
		return customerReturn;
	}

	@Override
	public CustomerReturn deleteCustomer(int customerId) {
		// checking if customer enters valid customer id
		Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException("No Customer Foudn With Id " + customerId);
		}

		// extracting the customer object
		Customer customer = optionalCustomer.get();

		TripBooking tripBooking = customer.getTripBooking();
		if (tripBooking != null) {
			tripBooking.setCustomerId(null);
			customer.setTripBooking(null);
		}

		CustomerReturn customerReturn = new CustomerReturn(customer.getCustomerId(), customer.getUsername(),
				customer.getPassword(), customer.getMobileNumber(), customer.getEmail());
		this.customerRepository.delete(customer);

		return customerReturn;
	}

	@Override
	public List<CustomerReturn> viewCustomers() {

		List<Customer> customers = this.customerRepository.findAll();
		List<CustomerReturn> customerReturns = new ArrayList<CustomerReturn>();
		if (customers.size() <= 0) {
			throw new CustomerNotFoundException("No Customers Found");
		}

		for (Customer customer : customers) {
			CustomerReturn customerReturn = new CustomerReturn(customer.getCustomerId(), customer.getUsername(),
					customer.getPassword(), customer.getMobileNumber(), customer.getEmail());
			customerReturns.add(customerReturn);
		}
		return customerReturns;
	}

	@Override
	public CustomerReturn viewCustomer(int customerId) {

		// checking if customer enters valid customer id
		Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException("No Customer Foudn With Id " + customerId);
		}

		// extracting the customer object
		Customer customer = optionalCustomer.get();
		CustomerReturn customerReturn = new CustomerReturn(customer.getCustomerId(), customer.getUsername(),
				customer.getPassword(), customer.getMobileNumber(), customer.getEmail());

		return customerReturn;
	}

	@Override
	public CustomerReturn validateCustomer(String username, String password) {

		Customer customer = this.customerRepository.findByUsernameAndPassword(username, password);
		if (customer == null) {
			throw new CustomerNotFoundException("No Customer Found With Matching username and password");
		}

		CustomerReturn customerReturn = new CustomerReturn(customer.getCustomerId(), customer.getUsername(),
				customer.getPassword(), customer.getMobileNumber(), customer.getEmail());
		return customerReturn;
	}

//	@Override
//	public Customer insertCustomer(Customer customer) {
//		Customer cust1 = this.customerRepository.findByUsername(customer.getUsername());
//		if(cust1 != null) {
//			throw new RuntimeException("Trying to enter a duplicate customer");
//		}
//		return this.customerRepository.save(customer);
//	}
//
//	@Override
//	public Customer updateCustomer(Customer customer) {
//		Optional<Customer> optionalCustomer = this.customerRepository.findById(customer.getCustomerId());
//		if (optionalCustomer.isEmpty()) {
//			throw new CustomerNotFoundException("No Customer found with customer id " + customer.getCustomerId());
//		}
//		return this.customerRepository.save(customer);
//	}
//
//	@Override
//	public Customer deleteCustomer(Customer customer) {
//		Optional<Customer> optionalCustomer = this.customerRepository.findById(customer.getCustomerId());
//		if (optionalCustomer.isEmpty()) {
//			throw new CustomerNotFoundException("No Customer found with customer id " + customer.getCustomerId());
//		}
//		this.customerRepository.delete(customer);
//		return customer;
//	}
//
//	@Override
//	public List<Customer> viewCustomers() {
//		List<Customer> allCustomers = this.customerRepository.findAll();
//		if (allCustomers.size() <= 0) {
//			throw new CustomerNotFoundException("No Customers Found");
//		}
//		return allCustomers;
//	}
//
//	@Override
//	public Customer viewCustomer(int customerId) {
//		Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);
//		if (optionalCustomer.isEmpty()) {
//			throw new CustomerNotFoundException("No Customer found with customer id " + customerId);
//		}
//		return optionalCustomer.get();
//	}
//
//	@Override
//	public Customer validateCustomer(String username, String password) {
//		Customer customer = this.customerRepository.findByUsernameAndPassword(username, password);
//		if (customer == null) {
//			throw new CustomerNotFoundException("No Customer Found With Matching credentials");
//		}
//		return customer;
//	}

}
