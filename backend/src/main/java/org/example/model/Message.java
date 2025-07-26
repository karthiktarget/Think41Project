package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Message {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    private String sender; // "USER" or "AI"
    private String content;
    private LocalDateTime timestamp;

    public Message() {
        this.id = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
    }

}
