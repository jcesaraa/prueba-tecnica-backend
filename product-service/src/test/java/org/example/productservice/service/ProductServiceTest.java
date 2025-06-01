package org.example.productservice.service;

import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product(1L, "Producto Test", BigDecimal.valueOf(100));
    }

    @Test
    void createProduct_ShouldSaveAndReturnProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product created = productService.createProduct(product);

        assertNotNull(created);
        assertEquals(product.getNombre(), created.getNombre());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void getProductById_WhenProductExists_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product found = productService.getProductById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void getProductById_WhenProductDoesNotExist_ShouldThrowException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(999L);
        });
    }

    @Test
    void getAllProducts_ShouldReturnPagedProducts() {
        List<Product> productList = Arrays.asList(product);
        Page<Product> page = new PageImpl<>(productList);
        Pageable pageable = PageRequest.of(0, 10);

        when(productRepository.findAll(pageable)).thenReturn(page);

        Page<Product> result = productService.getAllProducts(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void updateProduct_WhenProductExists_ShouldUpdateAndReturn() {
        Product updatedDetails = new Product(null, "Producto Actualizado", BigDecimal.valueOf(200));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product updated = productService.updateProduct(1L, updatedDetails);

        assertEquals("Producto Actualizado", updated.getNombre());
        assertEquals(BigDecimal.valueOf(200), updated.getPrecio());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteProduct_WhenProductExists_ShouldDelete() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void deleteProduct_WhenProductDoesNotExist_ShouldThrowException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct(999L);
        });
    }
}
