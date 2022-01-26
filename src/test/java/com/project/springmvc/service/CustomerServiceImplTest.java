package com.project.springmvc.service;

import com.project.springmvc.config.AppContext;
import com.project.springmvc.entity.Customer;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppContext.class, CustomerServiceImpl.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void saveAndGetCustomer() {
        Customer customer = new Customer();
        customerService.saveCustomer(customer);
        Customer customer1 = customerService.getCustomer(7);
        assertEquals(customer, customer1);
    }

    @Test
    void getCustomers() {
        List<Customer> customerEmptyRepository = customerService.getCustomers();
        Assertions.assertEquals("[]", customerEmptyRepository.toString());
        Customer customer = new Customer(),
                customer1 = new Customer(),
                customer2 = new Customer();
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer1);
        customerList.add(customer2);
        customerService.saveCustomer(customer);
        customerService.saveCustomer(customer1);
        customerService.saveCustomer(customer2);
        List<Customer> customerFilledRepository = customerService.getCustomers();
        assertEquals(customerList, customerFilledRepository);
    }

    @Test
    void deleteCustomer()  {
        Customer customer = new Customer();
        customerService.saveCustomer(customer);
        Customer customerById = customerService.getCustomer(6);
        assertEquals(customer, customerById);
        customerService.deleteCustomer(6);
        assertThat(customer).isNotIn(customerService);
    }
}