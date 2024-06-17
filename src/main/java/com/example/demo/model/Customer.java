package com.example.demo.model;

import jakarta.persistence.*;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author joseneto
 *
 */
@Entity
@Table(name = "customer")
public class Customer {

    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "first_name", nullable = false)
    private final String firstName;
    @Column(name = "last_name", nullable = false)
    private final String lastName;
    @Column(name = "email", nullable = false)
    private final String email;

    public Customer(final String firstName, final String lastName, final String email) {
        this.isValidEmail(email);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private void isValidEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "First Name: " + this.firstName
                + " Last Name: " + this.lastName
                + " Email: " + this.email;
    }
}