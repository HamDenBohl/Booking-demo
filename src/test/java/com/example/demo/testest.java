package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.model.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class testest {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    private User user;

    @BeforeEach
    public void BeforeTest() {
        customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setCreatedBy("system");
        customer.setCreatedDate(LocalDateTime.now());
        customer.setModifiedBy("system");
        customer.setModifiedDate(LocalDateTime.now());

        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhonenumber(1234567890);
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setSalt("randomSalt");
        user.setHashedPassword("hashedPassword");
        user.setUserType("admin");
        user.setInitials("JD");
        user.setCreatedBy("system");
        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedBy("system");
        user.setModifiedDate(LocalDateTime.now());

        user.setCustomer(customer);
        customer.setUser(user);
    }

    @Test
    @Transactional
    public void testCreateAndFindUser() {
        // Act
        userRepository.save(user);
        customerRepository.save(customer);

        // Assert
        User foundUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getFirstName()).isEqualTo("John");
        assertThat(foundUser.getLastName()).isEqualTo("Doe");
        assertThat(foundUser.getEmail()).isEqualTo("john.doe@example.com");
    }
}
