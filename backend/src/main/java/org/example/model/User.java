package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "app_user")
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

