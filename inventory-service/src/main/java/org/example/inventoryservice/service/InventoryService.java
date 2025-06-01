package org.example.inventoryservice.service;

import org.example.inventoryservice.model.InventoryItem;
import org.example.inventoryservice.repository.InventoryRepository;
import org.example.inventoryservice.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importar para transacciones
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private InventoryRepository repository;

    @Transactional
    public InventoryItem createItem(InventoryItem item) {
        logger.info("Creating item: {}", item.getNombre());
        return repository.save(item);
    }

    @Transactional
    public InventoryItem getItemById(Long id) {
        logger.info("Fetching item with ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item no encontrado con id: " + id));
    }

    @Transactional
    public Page<InventoryItem> getAllItems(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public InventoryItem updateItem(Long id, InventoryItem details) {
        logger.info("Evento: Inventario actualizado - Producto ID: {}", id);
        InventoryItem item = getItemById(id);
        item.setNombre(details.getNombre());
        item.setStock(details.getStock());
        return repository.save(item);
    }

    @Transactional
    public void deleteItem(Long id) {
        logger.info("Deleting item with ID: {}", id);
        InventoryItem item = getItemById(id);
        repository.delete(item);
    }

    // Usando los métodos personalizados
    @Transactional
    public List<InventoryItem> getItemsByName(String name) {
        logger.info("Searching items by name: {}", name);
        return repository.findByNombreContainingIgnoreCase(name);
    }

    @Transactional
    public List<InventoryItem> getItemsWithStockGreaterThan(int stock) {
        logger.info("Searching items with stock greater than or equal to: {}", stock);
        return repository.findByStockGreaterThanEqual(stock);
    }

    @Transactional
    public long countItemsByName(String name) {
        logger.info("Counting items with name containing: {}", name);
        return repository.countByNombreContainingIgnoreCase(name);
    }

    @Transactional
    public InventoryItem getItemWithLowestStock() {
        logger.info("Fetching item with the lowest stock");
        return repository.findTopByOrderByStockAsc();
    }
}
