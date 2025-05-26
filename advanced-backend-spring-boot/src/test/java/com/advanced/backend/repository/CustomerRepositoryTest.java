package com.advanced.backend.repository;

import com.advanced.backend.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository repo;

    @Test
    void testSaveAndFind() {
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setEmail("test@example.com");
        repo.save(customer);

        assertThat(repo.findAll()).hasSize(1);
    }
}

