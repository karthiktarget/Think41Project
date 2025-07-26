package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Data
@Entity
public class InventoryItem {
    @Id
    private Long id;

    @ManyToOne
    private Product product;

    private LocalDateTime createdAt;
    private LocalDateTime soldAt;
    private Double cost;

    @ManyToOne
    private DistributionCenter distributionCenter;
}

