package com.project.springmvc.controller;

import com.project.springmvc.entity.Customer;
import com.project.springmvc.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestCustomerController {

    private final CustomerService customerService;

    public RestCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/listCustomers")
    List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/saveCustomer")
    void saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    }

    @GetMapping("/listCustomers/{id}")
    Customer getCustomer(@PathVariable int id) throws NotFoundException {
        return customerService.getCustomer(id);
    }

    @PutMapping("/listCustomers/{id}")
    void updateCustomer(@RequestBody Customer customer, @PathVariable int id) throws NotFoundException {
        Customer customer1 = customerService.getCustomer(id);
        customer1.setFirst_name(customer.getFirst_name());
        customer1.setLast_name(customer.getLast_name());
        customer1.setEmail(customer.getEmail());
        customer1.setPhones(customer.getPhones());
        customerService.saveCustomer(customer1);
    }

    @DeleteMapping("/listCustomers/{id}")
    void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
