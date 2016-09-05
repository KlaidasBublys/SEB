package com.seb.tool.services.bundle;

import com.seb.tool.domain.bundle.Bundle;
import com.seb.tool.domain.customer.Customer;

public interface BundleService {
	
	Bundle suggestedBundle(Customer customer);
	
}
