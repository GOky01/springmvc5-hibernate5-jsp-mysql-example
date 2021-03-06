package com.project.springmvc.service;

import com.project.springmvc.entity.Customer;
import com.project.springmvc.repository.CustomerRepository;
import javassist.NotFoundException;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}





