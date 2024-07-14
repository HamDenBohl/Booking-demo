package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

   @Autowired
   ReservationRepository reservationRepository;

   public Reservation getReservation(Reservation reservation){
      Optional<Reservation> getReservation = reservationRepository.findById(reservation.getReservationId());
      if(getReservation.isEmpty()){
         new Reservation();
      }
      return getReservation.get();
   }

   public List<Reservation> getAllReservations(){
      List<Reservation> allReservations = reservationRepository.findAll();
      return allReservations;
   }


   public boolean createReservation(Reservation reservation){
      reservationRepository.save(reservation);
      return !reservationRepository.findById(reservation.getReservationId()).isEmpty();
   }

   public boolean editReservation(Reservation reservation){
      Optional<Reservation> existingReservation = reservationRepository.findById(reservation.getReservationId());
      if(existingReservation.isEmpty()){
         return false;
      }
      Reservation newReservation = new Reservation();
      newReservation.setReservationId(reservation.getReservationId());
      newReservation.setBooking(reservation.getBooking().get(0));
      newReservation.setCustomer(reservation.getCustomer());
      reservationRepository.save(reservation);
      return true;
   }

   public boolean deleteReservation(Reservation Reservation){
      Long ReservationId = Reservation.getReservationId();
      reservationRepository.delete(Reservation);
      return !reservationRepository.findById(ReservationId).isEmpty();
   }
   }
