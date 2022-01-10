package com.project.springmvc.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

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
    void save() throws SQLException {
        customer.setId(11);
        customer.setFirstName("Andrew");
        customer.setLastName("Silich");
        customer.setEmail("andrewsilich@lpnu.ua");

        assertEquals(11, customer.getId());
        assertEquals("Andrew", customer.getFirstName());
        assertEquals("Silich", customer.getLastName());
        assertEquals("andrewsilich@lpnu.ua", customer.getEmail());
        assertNotNull(customer);
    }

    @AfterEach
    void tearDown() {
        phoneNumber = null;
        assertNull(phoneNumber);
    }
}