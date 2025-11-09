package com.india.railway.controller;

import com.india.railway.yaml.ReadYamlFileProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.mysql.Customer_Mysql;
import com.india.railway.service.mysql.CustomerService_Mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
// Allow React Frontend
public class CustomerController_Mysql {

    @Autowired
    private CustomerService_Mysql customerService;




    /*
     * public List<Customer_Mysql> getAllCustomers() {
     * return customerService.getAllCustomers();
     * 
     * }
     * 
     */



    @GetMapping("/fetch_all_customers")
    public List<Map<String, Object>> getCustomerDropdown() {
        return customerService.getAllCustomers().stream().map(customer -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", customer.getId());
            map.put("name", customer.getName());
            return map;
        }).collect(Collectors.toList());
    }

    /*
     * @GetMapping("/{id}")
     * public Optional<Customer_Mysql> getCustomerById(@PathVariable Long id) {
     * return customerService.getCustomerById(id);
     * }
     */

    @PostMapping
    public Customer_Mysql addCustomer(@RequestBody Customer_Mysql customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer_Mysql updateCustomer(@PathVariable Long id, @RequestBody Customer_Mysql customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully!";
    }
}
