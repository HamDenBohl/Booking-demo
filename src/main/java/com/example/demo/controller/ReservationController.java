package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Reservation;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservation() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Reservation> getReservation(@RequestBody Long id) {
       Reservation reservation = reservationService.getReservation(id);
       return ResponseEntity.ok(reservation);
    }


    @PostMapping("/create")
    public ResponseEntity createReservation(@RequestBody Reservation reservation) {
        reservationService.createReservation(reservation);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/edit")
    public ResponseEntity<Reservation> editReservation(@RequestBody Reservation reservation) {
        Reservation editedReservation = reservationService.editReservation(reservation);
        return ResponseEntity.ok(editedReservation);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteReservation(@RequestBody Reservation reservation) {
        reservationService.deleteReservation(reservation);
        return ResponseEntity.ok().build();
    }


}
