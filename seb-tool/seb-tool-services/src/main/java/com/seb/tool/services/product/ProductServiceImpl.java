package com.seb.tool.services.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.seb.tool.domain.product.Product;
import com.seb.tool.services.rule.CreditCardRule;
import com.seb.tool.services.rule.CurrentAccountPlusRule;
import com.seb.tool.services.rule.CurrentAccountRule;
import com.seb.tool.services.rule.DebitCard;
import com.seb.tool.services.rule.GoldCreditCardRule;
import com.seb.tool.services.rule.JuniorSaverAccountRule;
import com.seb.tool.services.rule.StudentAccountRule;

@Service
public class ProductServiceImpl implements ProductService {

	private static final AtomicLong productId = new AtomicLong();
	
	private List<Product> products;
	
	public ProductServiceImpl() {
		products = init();
	}
	
	private List<Product> init() {
		List<Product> products = new ArrayList<>();
		products.add(new Product(productId.incrementAndGet(), "Current Account", new CurrentAccountRule(), true, false));
		products.add(new Product(productId.incrementAndGet(), "Current Account Plus", new CurrentAccountPlusRule(), true, false));
		products.add(new Product(productId.incrementAndGet(), "Junior Saver Account", new JuniorSaverAccountRule(), true, false));
		products.add(new Product(productId.incrementAndGet(), "Student Account", new StudentAccountRule(), true, false));
		products.add(new Product(productId.incrementAndGet(), "Debit Card", new DebitCard(), false, true));
		products.add(new Product(productId.incrementAndGet(), "Credit Card", new CreditCardRule(), false, false));
		products.add(new Product(productId.incrementAndGet(), "Gold Credit Card", new GoldCreditCardRule(), false, false));
		return products;
	}

	@Override
	public Product findById(long id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	@Override
	public List<Product> findAllProducts() {
		return products;
	}

	@Override
	public List<Object> extractRulesFromProducts(List<Product> products) {
		List<Object> rules = new ArrayList<>();
		if (products != null) {
			for (Product product : products) {
				rules.add(product.getProductRule());
			}
		}
		return rules;
	}

}
