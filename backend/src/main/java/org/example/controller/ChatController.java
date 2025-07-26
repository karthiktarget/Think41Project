package org.example.controller;

import org.example.dto.ChatRequest;
import org.example.dto.ChatResponse;
import org.example.model.Conversation;
import org.example.model.Message;
import org.example.repository.ConversationRepository;
import org.example.repository.MessageRepository;
import org.example.service.GroqClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ConversationRepository conversationRepo;

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private GroqClientService groqClientService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        Conversation conversation;
        if (request.getConversationId() != null) {
            Optional<Conversation> optional = conversationRepo.findById(request.getConversationId());
            conversation = optional.orElseGet(() -> conversationRepo.save(new Conversation()));
        } else {
            conversation = conversationRepo.save(new Conversation());
        }
        Message userMsg = new Message();
        userMsg.setConversation(conversation);
        userMsg.setSender("USER");
        userMsg.setContent(request.getMessage());
        messageRepo.save(userMsg);
        String aiReply = getLLMReply(request.getMessage());
        Message aiMsg = new Message();
        aiMsg.setConversation(conversation);
        aiMsg.setSender("AI");
        aiMsg.setContent(aiReply);
        messageRepo.save(aiMsg);

        return new ChatResponse(aiReply, conversation.getId());
    }
    private String getLLMReply(String prompt) {
        return groqClientService.getLLMReply(prompt);
    }
}
