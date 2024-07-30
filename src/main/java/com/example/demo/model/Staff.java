package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "phonenumber", nullable = false)
    private Integer phonenumber;
    @Column(name = "email", nullable = false)
    private String email;
    @JsonIgnore
    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;
    @JsonIgnore
    @NotBlank
    @Column(name = "salt", nullable = false)
    private String salt;
    @JsonIgnore
    @NotBlank
    @Column(name = "hashedPassword", nullable = false)
    private String hashedPassword;
    @JsonIgnore
    @Column(name = "userType", nullable = false)
    private String userType;
    @Column(name = "initials")
    private String initials;
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedule;


}
