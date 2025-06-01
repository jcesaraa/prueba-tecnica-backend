package org.example.inventoryservice.controller;

import org.example.inventoryservice.model.InventoryItem;
import org.example.inventoryservice.response.ApiResponse;
import org.example.inventoryservice.service.InventoryService;
import org.example.inventoryservice.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ApiResponse<Object> createItem(@RequestBody InventoryItem item) {
        InventoryItem createdItem = inventoryService.createItem(item);
        return buildItemResponse(createdItem, "Item creado correctamente", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getItemById(@PathVariable Long id) {
        try {
            InventoryItem item = inventoryService.getItemById(id);
            return buildItemResponse(item, "Item obtenido correctamente", HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return buildErrorResponse("Item no encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Object> updateItem(@PathVariable Long id, @RequestBody InventoryItem details) {
        try {
            InventoryItem updated = inventoryService.updateItem(id, details);
            return buildItemResponse(updated, "Item actualizado correctamente", HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return buildErrorResponse("Item no encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteItem(@PathVariable Long id) {
        try {
            inventoryService.deleteItem(id);
            return new ApiResponse<>(null, "Item eliminado correctamente", HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            return buildErrorResponse("Item no encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Utilidad para construir la respuesta JSON:API
    private ApiResponse<Object> buildItemResponse(InventoryItem item, String message, HttpStatus status) {
        Map<String, Object> attributes = new LinkedHashMap<>();
        attributes.put("id", item.getId());
        attributes.put("nombre", item.getNombre());
        attributes.put("stock", item.getStock());

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("type", "inventories");
        data.put("id", item.getId());
        data.put("attributes", attributes);

        return new ApiResponse<>(Collections.singletonMap("data", data), message, status);
    }

    private ApiResponse<Object> buildErrorResponse(String title, String detail, HttpStatus status) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("title", title);
        error.put("detail", detail);
        return new ApiResponse<>(Collections.singletonMap("errors", List.of(error)), title, status);
    }
}
