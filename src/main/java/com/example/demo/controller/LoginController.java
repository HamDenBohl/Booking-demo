package com.example.demo.controller;

import com.example.demo.DAO.LoginRequestDAO;
import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import io.micrometer.common.util.internal.logging.Slf4JLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests only from this origin
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private BookingService bookingService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDAO loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        logger.info("Login successful for user: {}", username);
        return ResponseEntity.ok("Login successful for user: " + username);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Booking> getBooking(@RequestBody Long id) {
        Booking booking = bookingService.getBooking(id);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/create")
    public ResponseEntity createBooking(@RequestBody Booking booking) {
        bookingService.createBooking(booking);
        return ResponseEntity.ok().build();    }

    @PostMapping("/edit")
    public ResponseEntity<Booking> editBooking(@RequestBody Booking booking) {
        Booking editedBooking = bookingService.editBooking(booking);
        return ResponseEntity.ok(editedBooking);
    }

    @PostMapping("/delete")
    public ResponseEntity<Booking> deleteBooking(@RequestBody Booking booking) {
        bookingService.deleteBooking(booking);
        return ResponseEntity.ok().build();
    }


}
