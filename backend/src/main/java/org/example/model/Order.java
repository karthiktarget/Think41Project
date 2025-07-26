package org.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    private String status;
    private LocalDateTime orderDate;

    // Constructors
    public Order() {
    }

    public Order(Long id, String status, LocalDateTime orderDate) {
        this.id = id;
        this.status = status;
        this.orderDate = orderDate;
    }
}


