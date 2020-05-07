package com.eliz.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eliz.springdemo.entity.Customer;
import com.eliz.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	public CustomerService theCustomerService;
	
	@GetMapping("/customers")
	public List getCustomers() {
		
		return	theCustomerService.getCustomers();
		
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = theCustomerService.getCustomer(customerId);
		if(theCustomer == null) {
			throw new CustomerNotFoundException("Customer Not Found:"+customerId);
		}
		return	theCustomer;
		
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		theCustomer.setId(0);
		theCustomerService.saveCustomer(theCustomer);
		return	theCustomer;
		
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		
		theCustomerService.saveCustomer(theCustomer);
		return	theCustomer;
		
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer checkCustomer = theCustomerService.getCustomer(customerId);
		if(checkCustomer == null) {
			throw new CustomerNotFoundException("Customer Id not Found:"+customerId);
		}
				
		 theCustomerService.deleteCustomer(customerId);
		return "Deleted Customer:"+customerId;		
	}


}
