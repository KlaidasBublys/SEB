package com.seb.tool.services.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seb.tool.api.rule.manager.RuleManager;
import com.seb.tool.domain.bundle.Bundle;
import com.seb.tool.domain.customer.Customer;
import com.seb.tool.services.product.ProductService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final AtomicLong customerId = new AtomicLong();
	
	@Autowired
	private RuleManager ruleManager;
	@Autowired
	private ProductService productService;
	private List<Customer> customers;
	
	public CustomerServiceImpl() {
		customers = init();
	}

	private List<Customer> init() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(customerId.incrementAndGet(), 15, false, 0d));
		customers.add(new Customer(customerId.incrementAndGet(), 18, true, 1000d));
		customers.add(new Customer(customerId.incrementAndGet(), 28, false, 50000d));
		return customers;
	}

	@Override
	public Customer findById(long id) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}
	
	@Override
	public void createCustomer(Customer customer) {
		customer.setId(customerId.incrementAndGet());
		customers.add(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		int index = customers.indexOf(customer);
		// validate before update
		ruleManager.initRules(productService.extractRulesFromProducts(customer.getProducts()));
		boolean isValidProducts = ruleManager.executeRules(new Object[] { customer });
		if (isValidProducts) {
			customers.set(index, customer);
		} else {
			System.out.println("Cannot update Customer because of failed products rules");
		}
		
	}

	@Override
	public boolean isCustomerExist(Customer customer) {
		return findById(customer.getId()) != null;
	}

	@Override
	public void assignBundle(Bundle bundle, Customer customer) {
		customer.setProducts(bundle.getProducts());
	}

}
