package com.india.railway.service.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.railway.model.mysql.Customer_Mysql;
import com.india.railway.repository.mysql.Customer_Repo_Mysql;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService_Mysql {

    @Autowired
    private Customer_Repo_Mysql customerRepository;

    public List<Customer_Mysql> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer_Mysql> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer_Mysql addCustomer(Customer_Mysql customer) {
        return customerRepository.save(customer);
    }

    public Customer_Mysql updateCustomer(Long id, Customer_Mysql updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updatedCustomer.getName());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setPhone(updatedCustomer.getPhone());
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
