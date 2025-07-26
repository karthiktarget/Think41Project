package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Conversation {

    @Id
    private UUID id;

    private LocalDateTime startedAt;

    public Conversation() {
        this.id = UUID.randomUUID();
        this.startedAt = LocalDateTime.now();
    }

}
