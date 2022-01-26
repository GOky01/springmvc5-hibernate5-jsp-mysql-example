package com.project.springmvc.service;

import com.project.springmvc.config.AppContext;
import com.project.springmvc.entity.Customer;
import com.project.springmvc.entity.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppContext.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
class CustomerServiceIT {

    private final CustomerService customerServiceMockitoInstance = Mockito.mock(CustomerServiceImpl.class);

    private final PhoneService phoneServiceMockitoInstance = Mockito.mock(PhoneServiceImpl.class);

    @Test
    public void whenCustomerAndPhoneIsProvidedThenRetrievedResultsIsCorrect() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirst_name("Andrew");
        customer.setLast_name("Silica");
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
        assertEquals("Andrew", mockitoInstanceCustomer.getFirst_name());
        assertEquals("Silica", mockitoInstanceCustomer.getLast_name());
        assertEquals("andriy715@google.com", mockitoInstanceCustomer.getEmail());
        Phone mockitoInstancePhone = phoneServiceMockitoInstance.getPhone(1);
        assertEquals(1, mockitoInstancePhone.getPhone_id());
        assertEquals("7461613251", mockitoInstancePhone.getPhone());
    }


}