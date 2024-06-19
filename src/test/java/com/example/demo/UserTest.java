package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    public void userIdTest(){
        User user = new User();
        user.setName("test");
        user.setPassword(UUID.randomUUID().toString());
        user.setEmail("test@test.dk");
        user.setPhonenumber(12345678);

        //entityManager.getTransaction().begin();
    //    entityManager.getTransaction().commit();






    }


}
