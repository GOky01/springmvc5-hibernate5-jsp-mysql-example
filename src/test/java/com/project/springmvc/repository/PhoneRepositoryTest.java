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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppContext.class, PhoneRepository.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class PhoneRepositoryTest {

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    public void saveAndGetPhone() {

        Customer customer = new Customer();
        customer.setFirstName("testName");
        customer.setLastName("testLastName");
        customer.setEmail("test@gmail.com");
        Phone phone1 = new Phone(0, "123613143");

        phoneRepository.save(phone1);
        Phone phoneById = phoneRepository.findById(2).get();
        assertAll("Field`s values equals",
                () -> assertEquals(phone1.getPhone_id(), phoneById.getPhone_id()),
                () -> assertEquals(phone1.getPhone(), phoneById.getPhone()));
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