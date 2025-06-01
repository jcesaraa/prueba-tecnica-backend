package org.example.inventoryservice.health;

import org.example.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InventoryHealthIndicator implements HealthIndicator {

    private final InventoryRepository repository;

    // Constructor para inyectar el repositorio de inventario
    public InventoryHealthIndicator(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Health health() {
        try {
            // Contamos los elementos en la base de datos
            long count = repository.count();
            // Si la conexión y los datos son correctos, retornamos Health.up()
            return Health.up()
                    .withDetail("message", "Database connection OK")
                    .withDetail("inventory_count", count)
                    .build();
        } catch (Exception e) {
            // Si ocurre un error, retornamos Health.down() con detalles
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}
