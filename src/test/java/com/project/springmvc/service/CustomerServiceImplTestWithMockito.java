package com.project.springmvc.service;

import com.project.springmvc.config.AppContext;
import com.project.springmvc.entity.Customer;
import com.project.springmvc.entity.Phone;
import com.project.springmvc.util.CustomerAndPhoneServiceConfiguration;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppContext.class, CustomerAndPhoneServiceConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
class CustomerServiceImplTestWithMockito {
    @Autowired
    private CustomerService customerServiceMockitoInstance;
    @Autowired
    private PhoneService phoneServiceMockitoInstance;

    @Test
    public void whenCustomerAndPhoneIsProvidedThenRetrievedResultsIsCorrect() throws NotFoundException {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Andrew");
        customer.setLastName("Silica");
        customer.setEmail("andriy715@google.com");
        Phone phone = new Phone();
        phone.setPhone_id(1);
        phone.setPhone("7461613251");
        phone.setCustomer(customer);
        customerServiceMockitoInstance.saveCustomer(customer);
        phoneServiceMockitoInstance.savePhone(phone);
        Mockito.when(customerServiceMockitoInstance.getCustomer(1)).thenReturn(customer);
        Mockito.when(phoneServiceMockitoInstance.getPhone(1)).thenReturn(phone);
        Customer mockitoInstanceCustomer = customerServiceMockitoInstance.getCustomer(1);
        assertEquals(1, mockitoInstanceCustomer.getId());
        assertEquals("Andrew", mockitoInstanceCustomer.getFirstName());
        assertEquals("Silica", mockitoInstanceCustomer.getLastName());
        assertEquals("andriy715@google.com", mockitoInstanceCustomer.getEmail());
        Phone mockitoInstancePhone = phoneServiceMockitoInstance.getPhone(1);
        assertEquals(1, mockitoInstancePhone.getPhone_id());
        assertEquals("7461613251", mockitoInstancePhone.getPhone());
        assertEquals(customer, mockitoInstancePhone.getCustomer());
        assertEquals(customer.getId(), mockitoInstancePhone.getCustomer().getId());
        assertEquals(customer.getFirstName(), mockitoInstancePhone.getCustomer().getFirstName());
        assertEquals(customer.getLastName(), mockitoInstancePhone.getCustomer().getLastName());
        assertEquals(customer.getEmail(), mockitoInstancePhone.getCustomer().getEmail());
    }
}