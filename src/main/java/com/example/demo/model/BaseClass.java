package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class BaseClass {

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private long createdDate;
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;
    @LastModifiedDate
    @Column(name = "modified_date")
    private long modifiedDate;
    @Column(name = "modified_by", nullable = false)
    private String modifiedBy;

}
