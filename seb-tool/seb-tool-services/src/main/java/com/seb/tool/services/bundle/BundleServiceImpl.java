package com.seb.tool.services.bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seb.tool.api.rule.manager.RuleManager;
import com.seb.tool.domain.bundle.Bundle;
import com.seb.tool.domain.customer.Customer;
import com.seb.tool.domain.product.Product;
import com.seb.tool.services.product.ProductService;

@Service
public class BundleServiceImpl implements BundleService {

	private static final AtomicLong bundleId = new AtomicLong();
	
	@Autowired
	private ProductService productService;
	@Autowired
	private RuleManager ruleManager;
	private List<Bundle> bundles;
	
	public void init() {
		bundles = new ArrayList<>();
		bundles.add(new Bundle(bundleId.incrementAndGet(), "Junior Saver", includedProducts(3) , 0));
		bundles.add(new Bundle(bundleId.incrementAndGet(), "Student", includedProducts(4, 5, 6) , 0));
		bundles.add(new Bundle(bundleId.incrementAndGet(), "Classic", includedProducts(1, 5) , 1));
		bundles.add(new Bundle(bundleId.incrementAndGet(), "Classic Plus", includedProducts(1, 5, 6) , 2));
		bundles.add(new Bundle(bundleId.incrementAndGet(), "Gold", includedProducts(2, 5, 7) , 3));
	}

	private List<Product> includedProducts(Integer... productsIds) {
		List<Product> includedProducts = new ArrayList<>();
		for (Integer productId : productsIds) {
			Product product = productService.findById(productId);
			if (product.isDebitCard()) {
				List<Object> debitCardRule = new ArrayList<>();
				debitCardRule.add(product.getProductRule());
				ruleManager.initRules(debitCardRule);
			}
			includedProducts.add(product);
		}
		// before return list needed validate all context
		boolean isAccountExist = ruleManager.executeRules(new Object[] { includedProducts });
		if (isAccountExist) {
			return includedProducts;
		} else {
			return null;
		}
	}

	@Override
	public Bundle suggestedBundle(Customer customer) {
		List<Bundle> passedBundles = new ArrayList<>();
		for (Bundle bundle : bundles) {
			if (bundle.getProducts() != null) {
				ruleManager.initRules(productService.extractRulesFromProducts(bundle.getProducts()));
				boolean isBundlePassed = ruleManager.executeRules(new Object[] { customer });
				if (isBundlePassed) {
					passedBundles.add(bundle);
				}
			}
		}
		return getMostValuableBundle(passedBundles);
	}

	private Bundle getMostValuableBundle(List<Bundle> bundles) {
		if (bundles.size() != 0) {
			return Collections.max(bundles, new BundleComparator());
		} else {
			return null;
		}
	}
	
	class BundleComparator implements Comparator<Bundle> {

		@Override
		public int compare(Bundle o1, Bundle o2) {
			Integer value1 = o1.getValue();
			Integer value2 = o2.getValue();
			return value1.compareTo(value2);
		}
		
	}
	
}
