package com.seb.tool.domain.customer;

import java.util.List;

import com.seb.tool.domain.product.Product;

public class Customer {

	private long id;
	private int age;
	private boolean isStudent;
	private double income;
	private List<Product> products;
	
	public Customer() {
		id = 0;
	}
	
	public Customer(long id, int age, boolean isStudent, double income) {
		this.id = id;
		this.age = age;
		this.isStudent = isStudent;
		this.income = income;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isStudent() {
		return isStudent;
	}
	
	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}
	
	public double getIncome() {
		return income;
	}
	
	public void setIncome(double income) {
		this.income = income;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Customer) {
			Customer other = (Customer) obj;
			return 	id == other.id && 
					age == other.age && 
					isStudent == other.isStudent && 
					income == other.income;
		} else {
			return false;
		}
	}
	
}
