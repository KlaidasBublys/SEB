package com.seb.tool.web.service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.seb.tool.domain.customer.Customer;
import com.seb.tool.services.customer.CustomerService;

@RestController
public class CustomerRestController implements RestControllerMarker {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
		Customer customer = customerService.findById(id);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/customer/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		if (customerService.isCustomerExist(customer)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		customerService.createCustomer(customer);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		Customer currentCustomer = customerService.findById(id);
		if (currentCustomer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		currentCustomer.setAge(customer.getAge());
		currentCustomer.setStudent(customer.isStudent());
		currentCustomer.setIncome(customer.getIncome());
		currentCustomer.setProducts(customer.getProducts());
		customerService.updateCustomer(currentCustomer);
		return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	}
	
}
