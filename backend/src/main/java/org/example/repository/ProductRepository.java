package org.example.repository;

import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom queries (optional)
    List<Product> findByCategory(String category);
    List<Product> findTop5ByOrderByRetailPriceDesc(); // or soldCount if tracked
}
