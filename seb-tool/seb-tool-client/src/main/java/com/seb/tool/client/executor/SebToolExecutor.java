package com.seb.tool.client.executor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.seb.tool.domain.customer.Customer;

public class SebToolExecutor {
	
	public static final String REST_SERVICES_URL = "http://localhost:8080";
	
	public static void main(String[] args) {
		// Create Customer
		System.out.println("Create Customer using REST API");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		Customer customer = new Customer();
		URI location = restTemplate.postForLocation(REST_SERVICES_URL + "/customer/", customer, Customer.class);
		System.out.println("Customer created using REST API");
		// Find created Customer
		System.out.println("Find created Customer using REST API");
		Customer createdCustomer = restTemplate.getForObject(location, Customer.class);
		if (createdCustomer != null ) {
			System.out.println("Founded!");
		}
		System.out.println("Customer founded using REST API");
		// Update founded Customer
		System.out.println("Update founded Customer using REST API");
		Customer updatedCustomer = new Customer();
		updatedCustomer.setAge(16);
		updatedCustomer.setIncome(50000);
		updatedCustomer.setStudent(false);
		restTemplate.put(location, updatedCustomer);
		System.out.println("Customer updated using REST API");
		System.out.println("Suggest bundles using REST API");
		ResponseEntity<List> suggestedBundles = restTemplate.postForEntity(REST_SERVICES_URL + "/bundle/", updatedCustomer, List.class);
		System.out.println(suggestedBundles.getBody());
	}
	
}
