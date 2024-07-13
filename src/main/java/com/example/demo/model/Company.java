package com.example.demo.model;


import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company")
public class Company extends BaseClass{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_name", nullable = false)
    private String companyname;
    @Column(name = "cvr", nullable = false)
    private Integer cvr;
    @Column(name = "phonenumber", nullable = false)
    private Integer phonenumber;
    @Column(name = "email", nullable = false)
    private String email;
}