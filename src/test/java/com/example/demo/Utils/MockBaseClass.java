package com.example.demo.Utils;

import com.example.demo.model.BaseClass;
import jakarta.persistence.MappedSuperclass;

// Mocking BaseClass
@MappedSuperclass
public class MockBaseClass extends BaseClass {

    public String getCreatedBy() {
        return "system"; // Mocking the createdBy field with a default value
    }

    public void setCreatedBy(String createdBy) {
        // Override to do nothing, as createdBy should be set automatically
    }

    // Implement other methods if needed
}
