package com.seb.tool.domain.bundle;

import java.util.List;

import com.seb.tool.domain.product.Product;

public class Bundle {

	private long id;
	private String bundleName;
	private List<Product> products;
	private Integer value;
	
	public Bundle() {}
	
	public Bundle(long id, String bundleName, List<Product> products, Integer value) {
		this.id = id;
		this.bundleName = bundleName;
		this.products = products;
		this.value = value;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getBundleName() {
		return bundleName;
	}
	
	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bundle Id: ");
		builder.append(this.id);
		builder.append(", Bundle Name: ");
		builder.append(this.bundleName);
		builder.append(", Bundle Products: ");
		builder.append(this.products);
		builder.append(", Bundle Value: ");
		builder.append(this.value);
		return builder.toString();
	}
	
}
