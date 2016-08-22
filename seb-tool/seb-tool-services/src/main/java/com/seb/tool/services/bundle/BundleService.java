package com.seb.tool.services.bundle;

import java.util.List;

import com.seb.tool.domain.bundle.Bundle;
import com.seb.tool.domain.customer.Customer;

public interface BundleService {
	
	List<Bundle> suggestedBundles(Customer customer);
	
	Bundle getMostValuableBundle(List<Bundle> bundles);
	
}
