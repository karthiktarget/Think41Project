package org.example.model;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    private Long id;

    private String firstName, lastName, email, gender;
    private Integer age;
    private String state, city, country, streetAddress, postalCode;
    private Double latitude, longitude;
    private String trafficSource;
    private LocalDateTime createdAt;
}

