package com.example.demo.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.model.Customer;
import com.example.demo.model.Test;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

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

    private Test user;

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

        user = new Test();
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

        customer.setUserId(1234L);
    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void testPersistAndFindUser() {
        // Arrange
        try (Connection connection = dataSource.getConnection()) {
            connection.beginRequest();
        //Act
            customerRepository.save(customer);
            userRepository.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Assert
        Test foundUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getFirstName()).isEqualTo("John");
        assertThat(foundUser.getLastName()).isEqualTo("Doe");
        assertThat(foundUser.getEmail()).isEqualTo("john.doe@example.com");
    }

    @org.junit.jupiter.api.Test
    public void testFindAndEditAndPersistUser() {
        // Arrange
        Test dbUser = new Test();
        try (Connection connection = dataSource.getConnection()) {
            connection.beginRequest();
            customerRepository.save(customer);
            userRepository.save(user);

            //Act
            Optional<Test> userFromDb = userRepository.findById(user.getId());
            userFromDb.get().setId(user.getId());
            userFromDb.get().setUserType("customer");
            userFromDb.get().setLastName("Bohl");
            userFromDb.get().setFirstName("Signe");
            userRepository.save(userFromDb.get());

            // Assert
            dbUser = userRepository.findById(userFromDb.get().getId()).orElse(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertThat(dbUser).isNotNull();
        assertThat(dbUser.getUserType()).isEqualTo("customer");
        assertThat(dbUser.getLastName()).isEqualTo("Bohl");
        assertThat(dbUser.getFirstName()).isEqualTo("Signe");
    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void testDeleteUser() {
        // Arrange
        try (Connection connection = dataSource.getConnection()) {
            connection.beginRequest();
            customerRepository.save(customer);
            userRepository.save(user);
            Test otherUser = user;
            otherUser.setUserType("customer");
            otherUser.setFirstName("test");
            userRepository.save(otherUser);
        //Act
            Long userId = user.getId();
            Long otherUserId = otherUser.getId();
            userRepository.delete(user);
            userRepository.deleteById(otherUser.getId());

        //Assert
            Test userFromDb = userRepository.findById(userId).orElse(null);
            Test otherUserFromDb = userRepository.findById(otherUserId).orElse(null);

            assertThat(userFromDb).isNull();
            assertThat(otherUserFromDb).isNull();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
