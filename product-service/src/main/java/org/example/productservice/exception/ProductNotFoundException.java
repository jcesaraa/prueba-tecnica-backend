package org.example.productservice.exception;

/**
 * Excepción personalizada para indicar que un producto no fue encontrado.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructor que recibe un mensaje personalizado.
     *
     * @param message Mensaje de error detallado.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe el ID del producto no encontrado y genera un mensaje estándar.
     *
     * @param id Identificador del producto.
     */
    public ProductNotFoundException(Long id) {
        super("Producto no encontrado con id: " + id);
    }
}
