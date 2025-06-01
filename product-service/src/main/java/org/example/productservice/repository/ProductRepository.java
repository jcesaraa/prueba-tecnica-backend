package org.example.productservice.repository;

import org.example.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Métodos CRUD básicos ya incluidos en JpaRepository
}
