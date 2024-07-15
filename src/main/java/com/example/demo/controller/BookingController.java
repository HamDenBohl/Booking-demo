package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
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
