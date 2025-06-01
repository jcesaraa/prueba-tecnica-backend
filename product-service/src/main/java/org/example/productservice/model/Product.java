package org.example.productservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private BigDecimal precio;

    // Constructor vacío requerido por JPA
    public Product() {}

    // Constructor con @JsonCreator y @JsonProperty para compatibilidad con Jackson
    @JsonCreator
    public Product(
            @JsonProperty("id") Long id,
            @JsonProperty("nombre") String nombre,
            @JsonProperty("precio") BigDecimal precio
    ) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
