package com.example.demo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "booking")
public class Booking extends BaseClass{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;
}
