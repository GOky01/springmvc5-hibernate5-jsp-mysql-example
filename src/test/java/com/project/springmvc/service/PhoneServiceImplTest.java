package com.project.springmvc.service;

import com.project.springmvc.config.AppContext;
import com.project.springmvc.entity.Customer;
import com.project.springmvc.entity.Phone;
import com.project.springmvc.repository.CustomerRepository;
import com.project.springmvc.repository.PhoneRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppContext.class, PhoneServiceImpl.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
class PhoneServiceImplTest {

    @Resource
    PhoneService phoneService;

    @Resource
    PhoneRepository phoneRepository;

    @Test
    void saveAndGetPhone() throws NotFoundException {
        Phone phone = new Phone();
        phoneService.savePhone(phone);
        Phone customer1 = phoneService.getPhone(7);
        assertEquals(phone, customer1);
    }

    @Test
    void getPhones() {
        List<Phone> phoneEmptyRepository = phoneService.getPhones();
        Assertions.assertEquals("[]", phoneEmptyRepository.toString());
        Phone phone = new Phone(),
                phone1 = new Phone(),
                phone2 = new Phone();
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);
        phoneList.add(phone1);
        phoneList.add(phone2);
        phoneService.savePhone(phone);
        phoneService.savePhone(phone1);
        phoneService.savePhone(phone2);
        List<Phone> phoneFilledRepository = phoneService.getPhones();
        assertEquals(phoneList, phoneFilledRepository);
    }

    @Test
    void deletePhone() throws NotFoundException {
        Phone phone = new Phone();
        phoneService.savePhone(phone);
        Phone phoneById = phoneService.getPhone(6);
        assertEquals(phone, phoneById);
        phoneRepository.deleteById(6);
        assertThat(phone).isNotIn(phoneService);
        assertThat(phone).isNotIn(phoneRepository);
    }
}