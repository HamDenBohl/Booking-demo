package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryUser {

    @Autowired
    UserService userService;

    User testUser = new User();

    @BeforeEach
    public void beforeTest() {
        userService.setPassword("test", testUser);
    }

    @Test
    public void userIdTest() {
        User user = new User();
        user.setFirstName("test");
        userService.setPassword("test", user);
        user.setEmail("test@test.dk");
        user.setPhonenumber(12345678);

        boolean passwordsMatch = userService.checkPassword("test", user);
        Assertions.assertTrue(passwordsMatch);
    }
}
