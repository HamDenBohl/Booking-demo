package com.example.demo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;
import java.util.regex.Pattern;
import java.util.List;

@Entity
//@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer")
public class Customer extends BaseClass {
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
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

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    @Override
    public String toString() {
        return "First Name: " + this.firstName
                + " Last Name: " + this.lastName
                + " Email: " + this.email;
    }
    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }
}