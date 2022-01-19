package com.project.springmvc.service;

import com.project.springmvc.config.AppContext;
import com.project.springmvc.entity.Phone;
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
        classes = {AppContext.class, PhoneServiceImpl.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
class PhoneServiceImplTest {

    @Autowired
    private PhoneService phoneService;

    @Test
    void saveAndGetPhone() throws NotFoundException {
        Phone phone = new Phone();
        phone.setPhone_id(0);
        phone.setPhone("13471361235");
        phoneService.savePhone(phone);
        Phone phone1 = phoneService.getPhone(7);
        assertEquals(phone, phone1);
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
        phoneService.deletePhone(6);
        assertThat(phone).isNotIn(phoneService);
    }
}