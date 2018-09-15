package com.yujia.rest.webservices.restfulwebservices.supermarket;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CustomersResource {
	//Retrieve all users
	//retriveUser
	
	@Autowired
	private CustomerService service;

	@GetMapping("/users")	
	public List<Customer> retriveAllCustomers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Customer retriveUser(@PathVariable int id) {
		Customer customer = service.findOne(id);
		if(customer == null) throw new CustomerNotFoundException("Id-" + id);
		return customer;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody Customer customer) {
		Customer savedCustomer = service.save(customer);
		
		/*Create the URI of the location of the resource that is created.
		 * This will return the current request URI.
		 * */
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand( savedCustomer.getId() ).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
