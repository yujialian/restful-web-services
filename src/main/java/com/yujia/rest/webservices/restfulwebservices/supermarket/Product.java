package com.yujia.rest.webservices.restfulwebservices.supermarket;

public class Product {
	
	private String productName;
	private Double price;
	private Integer amount;
	
	public Product() {
		
	}
	
	public Product(String productName, Double price, Integer amount) {
		// TODO Auto-generated constructor stub
		this.productName = productName;
		this.price = price;
		this.amount = amount;
	}
	
	public String getProductName() {
		return productName;
	}

	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}

	public Double totalAmount(Integer amount) {
		return price * amount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void addAmount(Integer addition) {
		// TODO Auto-generated method stub
		this.amount = this.amount + addition;
	}

	public boolean isSufficient(Integer spend) {
		return this.amount - spend >= 0;
	}
	
	
	
}
