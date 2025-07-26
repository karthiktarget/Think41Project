package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class DataLoaderService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void loadData() {
        Product p = new Product();
        p.setId(1L);
        p.setName("Sample Product");
        p.setCategory("Shirts");
        p.setStock(100);
        p.setSoldCount(50);
        productRepository.save(p);
    }
}
