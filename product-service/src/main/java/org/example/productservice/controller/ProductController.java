package org.example.productservice.controller;

import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.model.Product;
import org.example.productservice.response.ApiResponse;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ApiResponse<Object> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);

        Map<String, Object> attributes = new LinkedHashMap<>();
        attributes.put("nombre", created.getNombre());
        attributes.put("precio", created.getPrecio());

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("type", "products");
        data.put("id", created.getId());
        data.put("attributes", attributes);

        return new ApiResponse<>(data, "Producto creado correctamente", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return buildProductResponse(product, "Producto encontrado", HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return buildErrorResponse("Producto no encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Object> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        try {
            Product updated = productService.updateProduct(id, productDetails);
            return buildProductResponse(updated, "Producto actualizado correctamente", HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return buildErrorResponse("Error al actualizar", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ApiResponse<>(null, "Producto eliminado correctamente", HttpStatus.NO_CONTENT);
        } catch (ProductNotFoundException e) {
            return buildErrorResponse("Producto no encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ApiResponse<Object> buildProductResponse(Product product, String message, HttpStatus status) {
        Map<String, Object> attributes = new LinkedHashMap<>();
        attributes.put("nombre", product.getNombre());
        attributes.put("precio", product.getPrecio());

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("type", "products");
        data.put("id", product.getId());
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
