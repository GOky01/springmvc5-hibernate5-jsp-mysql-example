import com.project.springmvc.config.TestJpaConfig;
import com.project.springmvc.entity.Customer;
import com.project.springmvc.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { TestJpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class InMemoryDbTest {

    @Resource
    private CustomerRepository customerRepository;

    @Test
    public void testCustomerSave() {

        Customer customer = new Customer();
        customer.setId(11);
        customer.setFirstName("testName");
        customer.setLastName("testLastName");
        customer.setEmail("test@gmail.com");
        customerRepository.save(customer);
        Customer customer1 = customerRepository.findById(11).get();
        assertEquals(customer.getFirstName(),customer1.getFirstName());
        assertEquals(customer.getId(),customer1.getId());
    }
}