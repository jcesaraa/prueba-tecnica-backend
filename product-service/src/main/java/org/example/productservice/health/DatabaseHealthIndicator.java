package org.example.productservice.health;

import org.example.productservice.repository.ProductRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    private final ProductRepository productRepository;

    public DatabaseHealthIndicator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Health health() {
        try {
            long count = productRepository.count();
            return Health.up()
                    .withDetail("message", "Database connection OK")
                    .withDetail("product_count", count)
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}
