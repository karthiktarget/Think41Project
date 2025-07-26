package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Data
@Entity
public class OrderItem {
    @Id
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @ManyToOne
    private InventoryItem inventoryItem;

    private String status;
    private LocalDateTime createdAt, shippedAt, deliveredAt, returnedAt;
}

