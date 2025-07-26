package org.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders") // reserved keyword
public class Order {
    @Id
    private Long Id;

    @ManyToOne
    private User user;

    private String status;
    private String gender;
    private LocalDateTime createdAt, returnedAt, shippedAt, deliveredAt;
    private Integer numOfItem;
}

