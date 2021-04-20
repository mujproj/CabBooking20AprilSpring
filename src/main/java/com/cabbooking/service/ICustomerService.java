package com.cabbooking.service;

import java.util.List;

import com.cabbooking.dto.CustomerAdd;
import com.cabbooking.dto.CustomerReturn;
import com.cabbooking.dto.CustomerUpdate;

public interface ICustomerService {
	public CustomerReturn insertCustomer(CustomerAdd addCustomer);
	public CustomerReturn updateCustomer(CustomerUpdate updateCustomer);
	public CustomerReturn deleteCustomer(int customerId);
	public List<CustomerReturn>viewCustomers();
	public CustomerReturn viewCustomer(int customerId);
	public CustomerReturn validateCustomer(String username, String password);
}