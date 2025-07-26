package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products") // Optional but recommended
public class Product {

    @Id
    private Long id;

    private String name;
    private String category;
    private Integer stock;
    private Integer soldCount;

    // Constructors
    public Product() {}

    public Product(Long id, String name, String category, Integer stock, Integer soldCount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.soldCount = soldCount;
    }

}


