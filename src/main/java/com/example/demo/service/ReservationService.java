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

    public Reservation getReservation(Long id) {
        Optional<Reservation> getReservation = reservationRepository.findById(id);
        if (getReservation.isEmpty()) {
            new Reservation();
        }
        return getReservation.get();
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> allReservations = reservationRepository.findAll();
        return allReservations;
    }


    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public Reservation editReservation(Reservation reservation) {
        Reservation newReservation = new Reservation();
        newReservation.setReservationId(reservation.getReservationId());
        newReservation.setBooking(reservation.getBooking().get(0));
        newReservation.setCustomer(reservation.getCustomer());
        reservationRepository.save(newReservation);
        return reservation;
    }

    public void deleteReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }
}
