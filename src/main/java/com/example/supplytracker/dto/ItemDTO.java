package com.example.supplytracker.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class ItemDTO {

    private Long id;

    @NotBlank(message = "Item name cannot be empty")
    @Size(min = 3, max = 50, message = "Item name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    @NotNull(message = "Supplier ID cannot be null")
    private Long supplierId;

    @NotNull(message = "Created date cannot be null")
    @FutureOrPresent(message = "Created date must be in the present or future")
    private LocalDateTime createdDate;

    public ItemDTO() {}

    public ItemDTO(Long id, String name, String category, Long supplierId, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.supplierId = supplierId;
        this.createdDate = createdDate;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
}