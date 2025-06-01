package org.example.productservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        logger.warn("Producto no encontrado: {}", ex.getMessage());
        return buildErrorResponse("Producto no encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        logger.error("Error inesperado", ex);
        return buildErrorResponse("Error interno", "Ocurrió un error inesperado. Por favor, intente más tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildErrorResponse(String title, String detail, HttpStatus status) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("status", status.value());
        error.put("title", title);
        error.put("detail", detail);
        error.put("timestamp", LocalDateTime.now());

        Map<String, Object> response = new HashMap<>();
        response.put("errors", List.of(error));

        return new ResponseEntity<>(response, status);
    }
}
