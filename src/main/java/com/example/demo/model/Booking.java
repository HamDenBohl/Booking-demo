package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID bookingId;

    @Column(name = "start_time", nullable = false)
    private final LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private final LocalDateTime endTime;

    @JoinColumn (name = "customer_id", nullable = false)
    private User user;

    public Booking(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        return "Booking ID: " + this.bookingId
                + "\nStartTime Date: " + this.startTime
                + "\nEndTime Date: " + this.endTime;
    }



}
