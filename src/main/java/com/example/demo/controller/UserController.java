package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.model.Test;
import com.example.demo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping ("/findUsers")
    public ResponseEntity<List<Test>> getAllUsers() {
        List<Test> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/findUser")
    public ResponseEntity<Test> getUser(@RequestBody Test user) {
        Test foundUser = userService.getUser(user);
        return ResponseEntity.ok(foundUser);
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody Test user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/edit")
    public ResponseEntity<Test> editUser(@RequestBody Test user) {
        Test editedUser = userService.editUser(user);
        return ResponseEntity.ok(editedUser);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody Test user) {
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody Test user) {
        userService.changePassword(user);
        return ResponseEntity.ok().build();
    }

}
