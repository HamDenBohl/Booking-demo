package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.Utils.ReflectionUtils;
import com.example.demo.model.BaseClass;
import com.example.demo.model.Customer;
import com.example.demo.model.Reservation;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTest {


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
        // Arrange
        try (Connection connection = dataSource.getConnection()) {


        //Act
            customerRepository.save(customer);

            userRepository.save(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Assert
        User foundUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getFirstName()).isEqualTo("John");
        assertThat(foundUser.getLastName()).isEqualTo("Doe");
        assertThat(foundUser.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    public void testEditUser() {
        // Arrange
        userRepository.save(user);
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setUserType("customer");
        newUser.setLastName("Bohl");
        newUser.setFirstName("Signe");

        // Act
        userRepository.save(newUser);

        // Assert
        User foundUser = userRepository.findById(newUser.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getFirstName()).isNotEqualTo(user.getFirstName());
        assertThat(foundUser.getLastName()).isNotEqualTo(foundUser.getLastName());
        assertThat(foundUser.getUserType()).isNotEqualTo(user.getUserType());
    }


}
