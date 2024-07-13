package com.example.demo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author joseneto
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer")
public class Customer extends BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}