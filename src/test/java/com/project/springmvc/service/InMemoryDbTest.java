package com.project.springmvc.service;

import com.project.springmvc.config.AppContext;
import com.project.springmvc.entity.Customer;
import com.project.springmvc.entity.Phone;
import com.project.springmvc.repository.CustomerRepository;
import com.project.springmvc.repository.PhoneRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppContext.class, CustomerServiceImpl.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class InMemoryDbTest {

    @Resource
    private CustomerRepository customerRepository;
    @Resource
    private PhoneRepository phoneRepository;

    @Test
    public void saveAndGetCustomer() {

        Customer customer = new Customer();
        Customer customer2 = new Customer();
        customer.setFirstName("testName");
        customer.setLastName("testLastName");
        customer.setEmail("test@gmail.com");
        Phone phone1 = new Phone(1, "123613143", customer);
        Phone phone2 = new Phone(2, "714361125", customer);
        Phone phone3 = new Phone(3, "613465123", customer2);
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

    @Test
    public void saveAndGetPhone() {

        Customer customer = new Customer();
        customer.setFirstName("testName");
        customer.setLastName("testLastName");
        customer.setEmail("test@gmail.com");
        Phone phone1 = new Phone(0, "123613143", customer);

        phoneRepository.save(phone1);
        Phone phoneById = phoneRepository.findById(2).get();
        assertAll("Field`s values equals",
                () -> assertEquals(phone1.getPhone_id(), phoneById.getPhone_id()),
                () -> assertEquals(phone1.getPhone(), phoneById.getPhone()),
                () -> assertEquals(phone1.getCustomer().getId(), phoneById.getCustomer().getId()),
                () -> assertEquals(phone1.getCustomer(), phoneById.getCustomer()));
    }

    @Test
    public void getPhones() {
        List<Phone> customerEmptyRepository = phoneRepository.findAll();
        Assertions.assertEquals("[]", customerEmptyRepository.toString());
        Phone phone = new Phone(),
                phone1 = new Phone(),
                phone2 = new Phone();
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);
        phoneList.add(phone1);
        phoneList.add(phone2);
        List<Phone> phoneList2 = phoneRepository.saveAll(phoneList);
        assertEquals(phoneList, phoneList2);
        List<Phone> phoneFilledRepository = phoneRepository.findAll();
        assertEquals(phoneList2, phoneFilledRepository);
    }

    @Test
    public void deletePhone() {
        Phone savedPhone = phoneRepository.save(new Phone());
        Phone phoneById = phoneRepository.getById(1);
        assertEquals(savedPhone, phoneById);
        phoneRepository.deleteById(1);
        assertThat(savedPhone).isNotIn(phoneRepository);
    }
}