package org.example.inventoryservice.repository;

import org.example.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

    // Buscar por nombre (caso insensible)
    List<InventoryItem> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por stock mayor o igual a un valor
    List<InventoryItem> findByStockGreaterThanEqual(int stock);

    // Buscar por nombre y stock mayor o igual a un valor
    List<InventoryItem> findByNombreContainingIgnoreCaseAndStockGreaterThanEqual(String nombre, int stock);

    // Buscar el item con el stock más bajo (útil para inventarios)
    InventoryItem findTopByOrderByStockAsc();

    // Contar items por nombre (útil para filtrado o categorías)
    long countByNombreContainingIgnoreCase(String nombre);

    // Eliminar por nombre (si es necesario realizar eliminaciones masivas o por categorías)
    void deleteByNombreContainingIgnoreCase(String nombre);
}
