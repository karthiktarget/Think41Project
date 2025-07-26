package org.example.model;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class DistributionCenter {
    @Id
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
}

