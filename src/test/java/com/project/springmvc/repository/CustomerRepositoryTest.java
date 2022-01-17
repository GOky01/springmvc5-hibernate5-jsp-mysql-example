package com.project.springmvc.repository;

import com.project.springmvc.config.AppContext;
import com.project.springmvc.entity.Customer;
import com.project.springmvc.entity.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppContext.class, CustomerRepository.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void saveAndGetCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("testName");
        customer.setLastName("testLastName");
        customer.setEmail("test@gmail.com");
        Phone phone1 = new Phone(1, "123613143");
        Phone phone2 = new Phone(2, "714361125");
        Phone phone3 = new Phone(3, "613465123");
        Set<Phone> phoneSet = new HashSet<>();
        phoneSet.add(phone1);
        phoneSet.add(phone2);
        phoneSet.add(phone3);
        customer.setPhones(phoneSet);
        customerRepository.save(customer);
        Customer customerById = customerRepository.findById(2).get();

        assertAll("Field`s values equals",
                () -> assertEquals(customer.getId(), customerById.getId()),
                () -> assertEquals(customer.getEmail(), customerById.getEmail()),
                () -> assertEquals(customer.getPhones(), customerById.getPhones()),
                () -> assertEquals(customer.getFirstName(), customerById.getFirstName()),
                () -> assertEquals(customer.getLastName(), customerById.getLastName()));
    }

    @Test
    public void getCustomers() {
        List<Customer> customerEmptyRepository = customerRepository.findAll();
        Assertions.assertEquals("[]", customerEmptyRepository.toString());
        Customer customer = new Customer(),
                customer1 = new Customer(),
                customer2 = new Customer();
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer1);
        customerList.add(customer2);
        List<Customer> customerList2 = customerRepository.saveAll(customerList);
        assertEquals(customerList, customerList2);
        List<Customer> customerFilledRepository = customerRepository.findAll();
        assertEquals(customerList2, customerFilledRepository);
    }

    @Test
    public void deleteCustomer() {
        Customer savedCustomer = customerRepository.save(new Customer());
        Customer customerById = customerRepository.getById(1);
        assertEquals(savedCustomer, customerById);
        customerRepository.deleteById(1);
        assertThat(savedCustomer).isNotIn(customerRepository);
    }
}