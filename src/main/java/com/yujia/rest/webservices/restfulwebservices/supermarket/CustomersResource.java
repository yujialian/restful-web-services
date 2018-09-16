package com.yujia.rest.webservices.restfulwebservices.supermarket;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yujia.rest.webservices.restfulwebservices.exception.MissingArgumentException;
import com.yujia.rest.webservices.restfulwebservices.exception.NotFoundException;

@RestController
public class CustomersResource {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/customers")	
	public List<Customer> retriveAllCustomers() {
		return service.findAllCustomers();
	}
	
	@PostMapping("/customers-recharge")	
	public Customer rechargeCustomers(@RequestBody Customer customer) {
		if(customer.getName() == null || customer.getWalletBalance() == null) throw new MissingArgumentException("Customer name or charge amount is missing! Please input the customer name and how much money you want to charge!");
		return service.chargeCustomer(customer);
	}
	
	@GetMapping("/customers/{id}")
	public Customer retriveCustomer(@PathVariable int id) {
		Customer customer = service.findCustomerById(id);
		if(customer == null) throw new NotFoundException("Id-" + id + " customer not found!");
		return customer;
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
		if(customer.getName() == null || customer.getWalletBalance() == null) {
			throw new MissingArgumentException("Please make sure customer name and wallet balance is not missing!");
		}
		
		Customer savedCustomer = service.createCustomer(customer);
		/*Create the URI of the location of the resource that is created.
		 * This will return the current request URI.
		 * */
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand( savedCustomer.getId() ).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return service.getAllProducts();
	}
	
	@DeleteMapping("/products")
	public void removeProduct(@RequestBody Product product) {
		if(product.getProductName() == null) {
			throw new MissingArgumentException("Please add the product name you want to delete!");
		}
		service.removeProduct(product.getProductName());
	}
	
	@PostMapping("/products")
	public Product createProducts(@RequestBody Product product) {
		if(product.getAmount() == null || product.getPrice() == null || product.getProductName() == null) {
			throw new MissingArgumentException("Product amount, price or product name is missing!");
		}
		Product savedProduct = service.createProduct(product);
		return savedProduct;
	}
	
	/*Retrieve certain customers all transactions by user id.*/
	@GetMapping("/customer-transactions/{id}")
	public List<Transaction> getCustomerTransactions(@PathVariable int id) {
		Customer customer = service.findCustomerById(id);
		if(customer == null) throw new NotFoundException("Id-" + id + "Customer not found!");
		return service.getCustomerTransactions(customer);
	}
	
	@GetMapping("/transactions/{id}")
	public Transaction retriveTransaction(@PathVariable int id) {
		Transaction transaction = service.findTransactionById(id);
		if(transaction == null) throw new NotFoundException("Id-" + id + " Transaction not found!");
		
		return transaction;
	}
	
	@PostMapping("/transactions")
	public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction) {
		if(transaction.getCustomerName() == null || transaction.getProducts() == null || transaction.getProducts().size() == 0) {
			throw new MissingArgumentException("Please provide customer name and products name and amount!");
		}
		Transaction savedTransaction = service.createTransaction(transaction);
		
		/*Create the URI of the location of the resource that is created.
		 * This will return the current request URI.
		 * */
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{transactionId}")
		.buildAndExpand( savedTransaction.getTransactionId() ).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/transactions")
	public List<Transaction> retriveAllTransactions() {
		return service.getAllTransactions();
	}
	
	@DeleteMapping("/transactions/{id}")
	public void removeTransactionById(@PathVariable int id) {
		service.removeTransaction(id);
	}
}
