package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

   @Autowired
   BookingRepository bookingRepository;

   @Autowired
   ReservationRepository reservationRepository;

   public Booking getBooking(Long Id){
      Optional<Booking> getBooking = bookingRepository.findById(Id);
      if(getBooking.isEmpty()){
         new Booking();
      }
      return getBooking.get();
   }

   public List<Booking> getAllBookings(){
      List<Booking> allBookings = bookingRepository.findAll();
      return allBookings;
   }


   public boolean createBooking(Booking booking){
      bookingRepository.save(booking);
      return !bookingRepository.findById(booking.getBookingId()).isEmpty();
   }

   public boolean editBooking(Booking booking){
      Optional<Booking> existingBooking = bookingRepository.findById(booking.getBookingId());
      if(existingBooking.isEmpty()){
         return false;
      }
      Booking newBooking = new Booking();
      newBooking.setStartTime(booking.getStartTime());
      newBooking.setEndTime(booking.getEndTime());
      newBooking.setBookingId(booking.getBookingId());
      bookingRepository.save(booking);
      return true;
   }

   public boolean deleteBooking(Booking booking){
      Long bookingId = booking.getBookingId();
      bookingRepository.delete(booking);
      return !bookingRepository.findById(bookingId).isEmpty();
   }
   }
