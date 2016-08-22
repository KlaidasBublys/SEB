package com.seb.tool.services.customer;

import com.seb.tool.domain.bundle.Bundle;
import com.seb.tool.domain.customer.Customer;

public interface CustomerService {

	Customer findById(long id);
		
	void createCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	boolean isCustomerExist(Customer customer);
	
	void assignBundle(Bundle bundle, Customer customer);
	
}
