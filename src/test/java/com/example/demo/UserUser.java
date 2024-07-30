package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class UserUser {

    @Autowired
    UserService userService;

    String testpassword;


    @Before
    public void setup(){
        User testUser = new User();
        userService.setPassword("test",testUser);
    }

    @Test
    public void userIdTest(){
        User user = new User();
        user.setFirstName("test");
        user.setPassword("test");
        user.setEmail("test@test.dk");
        user.setPhonenumber(12345678);


        String pass = user.getHashedPassword();

        String hashed_password = BCrypt.hashpw(testpassword, BCrypt.gensalt());

        Assertions.assertEquals(pass,hashed_password);



    }


}
