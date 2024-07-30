package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public Booking getBooking(Long Id) {
        Optional<Booking> getBooking = bookingRepository.findById(Id);
        if (getBooking.isEmpty()) {
            new Booking();
        }
        return getBooking.get();
    }

    public List<Booking> getAllBookings() {
        List<Booking> allBookings = bookingRepository.findAll();
        return allBookings;
    }


    public void createBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public Booking editBooking(Booking booking) {
        Optional<Booking> existingBooking = bookingRepository.findById(booking.getId());
        if (existingBooking.isEmpty()) {
            throw new RuntimeException();
        }
        Booking newBooking = new Booking();
        newBooking.setStartTime(booking.getStartTime());
        newBooking.setEndTime(booking.getEndTime());
        newBooking.setBookingId(booking.getId());
        bookingRepository.save(booking);
        return newBooking;
    }

    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }
}
