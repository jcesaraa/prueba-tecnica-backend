package org.example.inventoryservice.exception;

// Excepción personalizada para cuando un item no es encontrado
public class ItemNotFoundException extends RuntimeException {

    // Constructor que recibe un mensaje personalizado
    public ItemNotFoundException(String message) {
        super(message);
    }
}
