package com.project.springmvc.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    Phone phoneNumber = null;
    Customer customer = null;

    @BeforeEach
    void setUp() {
        assertNull(phoneNumber);
        assertNull(customer);
        customer = new Customer();
        phoneNumber = new Phone();
        assertNotNull(phoneNumber);
        assertNotNull(customer);
    }

    @Test
    void phoneFieldsCorresponding() {
        Phone phone;
        phoneNumber.setPhone_id(10);
        phoneNumber.setPhone("6432616311");
        assertEquals(10, phoneNumber.getPhone_id());
        assertEquals("6432616311", phoneNumber.getPhone());
        assertNotNull(phoneNumber);
        phone=phoneNumber;
        assertSame(phone, phoneNumber);
        assertEquals(phone, phoneNumber);
        assertInstanceOf(Phone.class, phoneNumber);
    }

    @AfterEach
    void tearDown() {
        phoneNumber = null;
        assertNull(phoneNumber);
    }
}