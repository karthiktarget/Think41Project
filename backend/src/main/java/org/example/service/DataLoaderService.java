package org.example.service;

import jakarta.annotation.PostConstruct;
import org.example.model.Conversation;
import org.example.model.Message;
import org.example.model.Product;
import org.example.repository.ConversationRepository;
import org.example.repository.MessageRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class DataLoaderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PostConstruct
    public void loadProductsFromCSV() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new ClassPathResource("products.csv").getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                Product p = new Product();
                p.setId(Long.parseLong(tokens[0]));
                p.setCost(Double.parseDouble(tokens[1]));
                p.setCategory(tokens[2]);
                p.setName(tokens[3]);
                p.setBrand(tokens[4]);
                p.setRetailPrice(Double.parseDouble(tokens[5]));
                p.setDepartment(tokens[6]);
                p.setSku(tokens[7]);

                // Optional: set default stock/sold count
                p.setStock(100);
                p.setSoldCount(0);

                productRepository.save(p);
            }

            System.out.println("✅ Products loaded successfully from CSV.");
        } catch (Exception e) {
            System.out.println("❌ Error loading products: " + e.getMessage());
        }
    }

    @PostConstruct
    public void insertSampleChat() {
        Conversation conversation = new Conversation();
        conversationRepository.save(conversation);

        Message msg = new Message();
        msg.setConversation(conversation);
        msg.setSender("USER");
        msg.setContent("What are the top 5 sold products?");
        messageRepository.save(msg);

        System.out.println("✅ Sample chat message inserted.");
    }
}
