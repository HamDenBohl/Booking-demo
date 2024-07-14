package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{Id}")
    public Booking getBooking(@RequestBody Long id) {
        return bookingService.getBooking(id);
    }


    @PostMapping("/create")
    public boolean createBooking(@RequestBody Booking booking) {
        bookingService.createBooking(booking);
        return true;
    }

    @PostMapping("/edit")
    public boolean editBooking(@RequestBody Booking booking) {
        bookingService.editBooking(booking);
        return true;
    }

    @PostMapping("/delete")
    public boolean deleteBooking(@RequestBody Booking booking) {
        bookingService.deleteBooking(booking);
        return true;
    }


}
