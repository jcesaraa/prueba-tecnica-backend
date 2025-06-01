package org.example.inventoryservice.health;

import org.example.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InventoryHealthIndicator implements HealthIndicator {

    private final InventoryRepository repository;

    public InventoryHealthIndicator(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Health health() {
        try {
            long count = repository.count();
            return Health.up()
                    .withDetail("message", "Database connection OK")
                    .withDetail("inventory_count", count)
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}
