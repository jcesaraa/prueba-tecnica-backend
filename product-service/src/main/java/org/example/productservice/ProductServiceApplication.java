package org.example.productservice;

import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Product(null, "Laptop", new BigDecimal("1200.00")));
                repository.save(new Product(null, "Smartphone", new BigDecimal("699.99")));
                repository.save(new Product(null, "Tablet", new BigDecimal("450.50")));
                System.out.println("Sample products initialized!");
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
