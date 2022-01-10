package com.project.springmvc.service;

import java.util.List;

import com.project.springmvc.entity.Customer;
import javassist.NotFoundException;

public interface CustomerService {

	List<Customer> getCustomers();

	void saveCustomer(Customer theCustomer);

	Customer getCustomer(int theId) throws NotFoundException;

	void deleteCustomer(int theId);
	
}
