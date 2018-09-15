package com.yujia.rest.webservices.restfulwebservices.supermarket;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;


import org.springframework.stereotype.Component;

@Component
public class CustomerService {

	private static Map<String, Customer> customers = new HashMap<>();
	private static Map<String, Product> productsInventory = new HashMap<>();
	private static Map<String, List<Transaction>> customerTransactionHistory = new HashMap<>();
	private static int transactionId = 0;
	private static int usersCount = 3;
	
	static {
		customers.put("Adam", new Customer(1, "Adam", new Date(), 100.1));
		customers.put("Eve", new Customer(2, "Eve", new Date(), 300.2));
		customers.put("Jack", new Customer(3, "Jack", new Date(), 500.3));
	}
	
	public Customer createCustomer(Customer customer) {
		if(customer.getId() == null) {
			customer.setId(++usersCount);
		}
		customers.put(customer.getName(), customer);
		return customer;
	}
	
	public Customer findCustomerById(int id) {
		for(Customer customer: customers.values()) {
			if(customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}
	
	/*Create a new product.*/
	public void createProduct(Product product) {
		if(productsInventory.containsKey(product.getProductName())) {
			productsInventory.get(product.getProductName()).addAmount(product.getAmount());
			productsInventory.get(product.getProductName()).setPrice(product.getPrice());
		} else {
			productsInventory.put(product.getProductName(), product);
		}
	}
	
	/*Delete an existing product.*/
	public Product removeProduct(Product product) {
		if(productsInventory.containsKey(product.getProductName())) {
			return productsInventory.remove(product.getProductName());
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
	public List<Transaction> getAllTransactions() {
		List<Transaction> allTransactions = new ArrayList<>();
		for(Entry<String, List<Transaction>> entry: customerTransactionHistory.entrySet()) {
			allTransactions.addAll(entry.getValue());
		}
		return allTransactions;
	}
	
	/*Retrieve orders for a specific customer.*/
	public List<Transaction> getCustomerTransactions(String customerName) {
		return customerTransactionHistory.get(customerName);
	}
	
	/*Create a new order.*/
	public Transaction createTransaction(Transaction transaction) {
		//Double totalSpend = productsInventory.get(transaction.productName).totalAmount(amount);
		
		Double totalSpend = 0.0;
		for(Map.Entry<String, Integer> entry: transaction.getProducts().entrySet()) {
			totalSpend += productsInventory.get(entry.getKey()).getPrice() * entry.getValue();
		}
		
		if(customers.get(transaction.getCustomerName()).isSufficient(totalSpend)) {
			customers.get(transaction.getCustomerName()).spendMoney(totalSpend);
			transaction.setTransactionId(++transactionId);
			if(!customerTransactionHistory.containsKey(transaction.getCustomerName())) {
				customerTransactionHistory.put(transaction.getCustomerName(), new ArrayList<Transaction>());
			}
			customerTransactionHistory.get(transaction.getCustomerName()).add(transaction);
			return transaction;
		}
		return null;
	}
	
	/*Delete an existing order.*/
	public boolean removeTransaction(int transactionId) {
		
		for(Map.Entry<String, List<Transaction>> entry: customerTransactionHistory.entrySet()) {
			for(int i = 0; i < entry.getValue().size(); i++) {
				if(entry.getValue().get(i).getTransactionId() == transactionId) {
					entry.getValue().remove(i);
					return true;
				}
			}
		}
		
		return false;
	}
	
}