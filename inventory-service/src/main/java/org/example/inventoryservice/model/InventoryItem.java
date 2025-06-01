package org.example.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor  // Lombok genera el constructor vacío
@AllArgsConstructor // Lombok genera el constructor con todos los campos
public class InventoryItem {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Min(value = 0, message = "El stock debe ser positivo")
    private int stock;
}
