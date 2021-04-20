package com.cabbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabbooking.entities.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
//	public Customer insertCustomer(Customer customer);
//	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
//	public Customer deleteCustomer(Customer customer) throws CustomerNotFoundException;
//	public List<Customer>viewCustomers() throws CustomerNotFoundException;
//	public Customer viewCustomer(int customerId) throws CustomerNotFoundException;

	public Customer findByUsername(String username);

	public Customer findByUsernameAndPassword(String username, String password);
}