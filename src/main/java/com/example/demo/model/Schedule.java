package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Date", nullable = false)
    private LocalDate date;
    @Column(name = "Start_time",nullable = false)
    private LocalTime startTime;
    @Column(name = "End_time",nullable = false)
    private LocalTime endTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Schedule_id", nullable = false)
    private Staff staff;

}
