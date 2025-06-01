package org.example.productservice.config;

import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Configuration
@Profile("dev")  // Solo activa en perfil desarrollo
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(ProductRepository repository) {
        return args -> {
            repository.save(new Product(null, "Smartphone", BigDecimal.valueOf(999.99)));
            repository.save(new Product(null, "Book", BigDecimal.valueOf(19.99)));
            repository.save(new Product(null, "Headphones", BigDecimal.valueOf(299.99)));
        };
    }
}
