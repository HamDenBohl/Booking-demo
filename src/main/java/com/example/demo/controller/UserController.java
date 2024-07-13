package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping ("/findUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/findUser")
    public User getUser(@RequestBody User user) {
        return userService.getUser(user);
    }


    @PostMapping("/create")
    public boolean createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/edit")
    public boolean editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestBody User user) {
        return userService.changePassword(user);
    }

}
