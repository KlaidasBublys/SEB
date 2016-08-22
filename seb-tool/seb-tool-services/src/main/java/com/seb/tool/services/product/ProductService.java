package com.seb.tool.services.product;

import java.util.List;

import com.seb.tool.domain.product.Product;

public interface ProductService {

	Product findById(long id);
	
	List<Product> findAllProducts();
	
	List<Object> extractRulesFromProducts(List<Product> products);
	
}
