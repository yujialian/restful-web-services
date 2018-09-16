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
	
	/* Some test data. */
	static {
		
		customers.put("Adam", new Customer(1, "Adam", new Date(), 10000.1));
		customers.put("Eve", new Customer(2, "Eve", new Date(), 3020.2));
		customers.put("Jack", new Customer(3, "Jack", new Date(), 5000.3));
		
		productsInventory.put("Iphone", new Product("Iphone", 1999.0, 10));
		productsInventory.put("Macbook Pro", new Product("Macbook Pro", 2999.0, 20));
		productsInventory.put("Imac", new Product("Imac", 3999.0, 50));
		productsInventory.put("Accessories", new Product("Accessories", 3.99, 100));
		
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
	
	public List<Customer> findAllCustomers() {
		List<Customer> allCustomer = new ArrayList<>();
		for(Customer customer: customers.values()) {
			allCustomer.add(customer);
		}
		return allCustomer;
	}
	
	/*Create a new product.*/
	public Product createProduct(Product product) {
		if(productsInventory.containsKey(product.getProductName())) {
			productsInventory.get(product.getProductName()).addAmount(product.getAmount());
			productsInventory.get(product.getProductName()).setPrice(product.getPrice());
		} else {
			productsInventory.put(product.getProductName(), product);
		}
		return productsInventory.get(product.getProductName());
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
	public List<Transaction> getAllTransactions() {
		List<Transaction> allTransactions = new ArrayList<>();
		for(Entry<String, List<Transaction>> entry: customerTransactionHistory.entrySet()) {
			allTransactions.addAll(entry.getValue());
		}
		return allTransactions;
	}
	
	/*Retrieve orders for a specific customer.*/
	public List<Transaction> getCustomerTransactions(Customer customer) {
		return customerTransactionHistory.get(customer.getName());
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
	
	/*Find an existing order.*/
	public Transaction findTransactionById(int transactionId) {
		
		for(Map.Entry<String, List<Transaction>> entry: customerTransactionHistory.entrySet()) {
			for(int i = 0; i < entry.getValue().size(); i++) {
				if(entry.getValue().get(i).getTransactionId() == transactionId) {
					return entry.getValue().get(i);
				}
			}
		}
		return null;
	}
	
}
