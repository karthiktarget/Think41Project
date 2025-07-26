package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String category;
    private String brand;
    private Double cost;
    private Double retailPrice;
    private String department;
    private String sku;
    private Integer stock;
    private Integer soldCount;

    @ManyToOne
    private DistributionCenter distributionCenter;
}



