package com.example.demo.model;

/**
 * @author joseneto
 *
 */
public class Reservation {

    private final Customer customer;
    private final Booking booking;

    public Reservation(final Customer customer,
                       final Booking booking) {
        this.customer = customer;
        this.booking = booking;

    }



    @Override
    public String toString() {
        return "Customer: " + this.customer.toString();
    }
}