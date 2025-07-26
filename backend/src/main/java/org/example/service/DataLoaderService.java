package org.example.service;

import org.example.model.Conversation;
import org.example.model.Message;
import org.example.repository.ConversationRepository;
import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class DataLoaderService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PostConstruct
    public void testInsert() {
        Conversation conversation = new Conversation();
        conversationRepository.save(conversation);

        Message msg = new Message();
        msg.setConversation(conversation);
        msg.setSender("USER");
        msg.setContent("What are the top 5 sold products?");
        messageRepository.save(msg);
    }

}
