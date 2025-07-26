package org.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GroqClientService {

    private final WebClient webClient;

    public GroqClientService(@Value("${groq.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.groq.com/openai/v1/chat/completions")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }

    public String getLLMReply(String userMessage) {
        Map<String, Object> request = Map.of(
                "model", "mixtral-8x7b-32768",
                "messages", new Object[]{
                        Map.of("role", "system", "content", "You are a helpful assistant for an e-commerce clothing website."),
                        Map.of("role", "user", "content", userMessage)
                }
        );

        try {
            Map<String, Object> response = webClient.post()
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
            if (choices != null && !choices.isEmpty()) {
                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

                String reply = message != null ? message.get("content").toString() : "I'm not sure how to help with that.";

                System.out.println("✅ LLM Request: " + userMessage);
                System.out.println("🤖 LLM Reply: " + reply);

                return reply;
            }

            return "Sorry, no response from the language model.";
        } catch (Exception e) {
            return "LLM Error: " + e.getMessage();
        }
    }
}
