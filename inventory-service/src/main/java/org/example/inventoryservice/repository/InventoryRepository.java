package org.example.inventoryservice.repository;

import org.example.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findByNombreContainingIgnoreCase(String nombre);

    List<InventoryItem> findByStockGreaterThanEqual(int stock);

    List<InventoryItem> findByNombreContainingIgnoreCaseAndStockGreaterThanEqual(String nombre, int stock);

    InventoryItem findTopByOrderByStockAsc();

    long countByNombreContainingIgnoreCase(String nombre);

    void deleteByNombreContainingIgnoreCase(String nombre);
}
