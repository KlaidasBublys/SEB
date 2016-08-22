package com.seb.tool.domain.product;

public class Product {

	private long id;
	private String productName;
	private Object productRule;
	private boolean isAccount;
	private boolean isDebitCard;
	
	public Product() {}
	
	public Product(long id, String productName, Object productRule, boolean isAccount, boolean isDebitCard) {
		this.id = id;
		this.productName = productName;
		this.productRule = productRule;
		this.isAccount = isAccount;
		this.isDebitCard = isDebitCard;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Object getProductRule() {
		return productRule;
	}

	public void setProductRule(Object productRule) {
		this.productRule = productRule;
	}

	public boolean isAccount() {
		return isAccount;
	}

	public void setAccount(boolean isAccount) {
		this.isAccount = isAccount;
	}

	public boolean isDebitCard() {
		return isDebitCard;
	}

	public void setDebitCard(boolean isDebitCard) {
		this.isDebitCard = isDebitCard;
	}
	
}
