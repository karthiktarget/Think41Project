package org.example.repository;

import org.example.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {}
