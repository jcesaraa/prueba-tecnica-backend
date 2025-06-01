package org.example.inventoryservice.service;

import org.example.inventoryservice.exception.ItemNotFoundException;
import org.example.inventoryservice.model.InventoryItem;
import org.example.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private InventoryItem inventory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventory = new InventoryItem(1L, "Laptop", 10);
    }

    @Test
    void createItem_ShouldReturnCreatedInventory() {
        when(inventoryRepository.save(any(InventoryItem.class))).thenReturn(inventory);

        InventoryItem createdInventory = inventoryService.createItem(inventory);

        assertNotNull(createdInventory);
        assertEquals("Laptop", createdInventory.getNombre());
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    void getItemById_ShouldReturnInventory() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        InventoryItem foundInventory = inventoryService.getItemById(1L);

        assertNotNull(foundInventory);
        assertEquals(1L, foundInventory.getId());
    }

    @Test
    void getItemById_ShouldThrowExceptionWhenItemNotFound() {
        when(inventoryRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> inventoryService.getItemById(999L));
    }

    @Test
    void updateItem_ShouldUpdateInventory() {
        InventoryItem updatedDetails = new InventoryItem(1L, "Updated Laptop", 20);
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));
        when(inventoryRepository.save(any(InventoryItem.class))).thenReturn(updatedDetails);

        InventoryItem updatedInventory = inventoryService.updateItem(1L, updatedDetails);

        assertEquals("Updated Laptop", updatedInventory.getNombre());
        assertEquals(20, updatedInventory.getStock());
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    void deleteItem_ShouldDeleteInventory() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        inventoryService.deleteItem(1L);

        verify(inventoryRepository, times(1)).delete(inventory);
    }
}
