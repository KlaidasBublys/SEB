package com.seb.tool.web.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seb.tool.domain.bundle.Bundle;
import com.seb.tool.domain.customer.Customer;
import com.seb.tool.services.bundle.BundleService;

@RestController
public class BundleRestController implements RestControllerMarker {

	@Autowired
	private BundleService bundleService;
	
	@RequestMapping(value = "/bundle/", method = RequestMethod.POST)
	public ResponseEntity<List<Bundle>> suggestedBundles(@RequestBody Customer customer) {
		List<Bundle> suggestedBundles = bundleService.suggestedBundles(customer);
		return new ResponseEntity<List<Bundle>>(suggestedBundles, HttpStatus.CREATED);
	}
	
}
