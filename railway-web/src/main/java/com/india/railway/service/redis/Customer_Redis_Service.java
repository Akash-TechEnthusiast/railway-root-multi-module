package com.india.railway.service.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.railway.model.Customer_Redis;
import com.india.railway.repository.redis.Customer_Redis_Repo;

@Service
public class Customer_Redis_Service {

	// Injecting Repository into service class

	@Autowired
	private Customer_Redis_Repo repo;

	// to insert new customer data into the Redis database
	public Customer_Redis addCustomer(Customer_Redis customer) {

		return repo.save(customer);
	}

	// run a fetch query in the Redis Database
	// to get a list of all the customers
	public List<Customer_Redis> getAllCustomers() {

		List<Customer_Redis> allCustomer = new ArrayList<>();
		repo.findAll().forEach(allCustomer::add);
		return allCustomer;
	}

	// fetch operation to get customer using an ID
	public Customer_Redis getCustomerById(Long id) {

		Optional<Customer_Redis> optionalCustomer = repo.findById(String.valueOf(id));
		return optionalCustomer.orElse(null);
	}

	// update operation to existing customer using an ID
	public Customer_Redis updateCustomerById(int id,
			Customer_Redis newCustomer) {

		Optional<Customer_Redis> existingCustomer = repo.findById(String.valueOf(id));

		if (existingCustomer.isPresent()) {
			Customer_Redis updatedCustomer = existingCustomer.get();

			updatedCustomer.setName(newCustomer.getName());
			updatedCustomer.setPhone(newCustomer.getPhone());
			updatedCustomer.setEmail(newCustomer.getEmail());

			repo.deleteById(String.valueOf(id));
			return repo.save(updatedCustomer);
		}

		return null;
	}

	// delete the existing customer
	public void deleteCustomerById(Long id) {
		repo.deleteById(String.valueOf(id));
	}

}
