package com.yujia.rest.webservices.restfulwebservices.supermarket;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CustomerService {
	private static List<Customer> customers = new ArrayList<Customer>();
	private static Map<String, Product> productsInventory = new HashMap<>();
	private static Map<Customer, List<Transaction>> customerTransactionHistory = new HashMap<>();
	private static int usersCount = 3;
	
	static {
		customers.add(new Customer(1, "Adam", new Date(), 100.1));
		customers.add(new Customer(1, "Eve", new Date(), 300.2));
		customers.add(new Customer(1, "Jack", new Date(), 500.3));
	}
	
	public Customer createCustomer(Customer customer) {
		if(customer.getId() == null) {
			customer.setId(++usersCount);
		}
		customers.add(customer);
		return customer;
	}
	
	public Customer findCustomer(int id) {
		for(Customer customer: customers) {
			if(customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}
	
	/*Create a new product.*/
	public void createProduct(String productName, Double price, Integer amount) {
		if(productsInventory.containsKey(productName)) {
			productsInventory.get(productName).addStock(amount);
		} else {
			productsInventory.put(productName, new Product(productName, price, amount));
		}
	}
	
	/*Delete an existing product.*/
	public Product removeProduct(String productName) {
		if(productsInventory.containsKey(productName)) {
			return productsInventory.remove(productName);
		}
		return null;
	}
	
	/*Retrieve a list of all products.*/
	public List<Product> getAllProducts() {
		List<Product> allProducts = new ArrayList<>();
		for(Map.Entry<String, Product> entry: productsInventory.entrySet()) {
			allProducts.add(entry.getValue());
		}
		return allProducts;
	}
	
	/*Retrieve a list of all orders.*/
	
	/*Create a new order.*/
	
	/*Delete an existing order.*/
	
}
