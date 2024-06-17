package com.example.demo.model;

import java.util.Date;
import java.util.UUID;

public class Booking {

    private final UUID bookingId = UUID.randomUUID();
    private final Date startTime;
    private final Date endTime;


    public Booking(Date startTime, Date endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public Date getStartTime() {
        return this.startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        return "Booking ID: " + this.bookingId
                + "\nStartTime Date: " + this.startTime
                + "\nEndTime Date: " + this.endTime;
    }



}
