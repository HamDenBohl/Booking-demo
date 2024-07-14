package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.model.Customer;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void BeforeTest(){
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

        Customer customer = new Customer();
        customer.setId(1L); // Set appropriate values for Customer entity
        user.setCustomer(customer);
    }

    @Test
    public void testCreateAndFindUser() {
        // Arrange

        // Act
        userRepository.save(user);

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
