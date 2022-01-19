package com.project.springmvc.util;

import com.project.springmvc.service.CustomerServiceImpl;
import com.project.springmvc.service.PhoneServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class CustomerAndPhoneServiceConfiguration {
    @Bean
    @Primary
    public CustomerServiceImpl customerServiceMockitoInstance() {
        return Mockito.mock(CustomerServiceImpl.class);
    }
    @Bean
    @Primary
    public PhoneServiceImpl phoneServiceMockitoInstance() {
        return Mockito.mock(PhoneServiceImpl.class);
    }
}
