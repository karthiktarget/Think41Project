package org.example.controller;

import org.example.dto.ChatRequest;
import org.example.dto.ChatResponse;
import org.example.model.Conversation;
import org.example.model.Message;
import org.example.repository.ConversationRepository;
import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Allow frontend access
public class ChatController {

    @Autowired
    private ConversationRepository conversationRepo;

    @Autowired
    private MessageRepository messageRepo;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        // Step 1: Get or create conversation
        Conversation conversation;
        if (request.getConversationId() != null) {
            Optional<Conversation> optional = conversationRepo.findById(request.getConversationId());
            conversation = optional.orElseGet(() -> {
                Conversation newConvo = new Conversation();
                return conversationRepo.save(newConvo);
            });
        } else {
            conversation = conversationRepo.save(new Conversation());
        }

        // Step 2: Save user message
        Message userMsg = new Message();
        userMsg.setConversation(conversation);
        userMsg.setSender("USER");
        userMsg.setContent(request.getMessage());
        messageRepo.save(userMsg);

        // Step 3: Call LLM (simulated for now)
        String aiReply = simulateLLMReply(request.getMessage());

        // Step 4: Save AI response
        Message aiMsg = new Message();
        aiMsg.setConversation(conversation);
        aiMsg.setSender("AI");
        aiMsg.setContent(aiReply);
        messageRepo.save(aiMsg);

        return new ChatResponse(aiReply, conversation.getId());
    }

    /**
     * Simulates a call to a Large Language Model (LLM) for generating a response.
     * In a real application, this would involve making an API call to an LLM service.
     *
     * @param prompt The user's message to the LLM.
     * @return A simulated response from the LLM.
     */
    private String simulateLLMReply(String prompt) {
        if (prompt.toLowerCase().contains("top 5")) {
            return "The top 5 sold products are: Shirt A, Shirt B, Pants C, Jacket D, and Dress E.";
        } else if (prompt.toLowerCase().contains("order status")) {
            return "Please provide the order ID to check the status.";
        }
        return "I'm not sure. Could you rephrase the question?";
    }
}
