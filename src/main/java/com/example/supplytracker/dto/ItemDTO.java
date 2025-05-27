package com.example.supplytracker.dto;

import java.time.LocalDateTime;

public class ItemDTO {
    private Long id;
    private String name;
    private String category;
    private Long supplierId;
    private LocalDateTime createdDate;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String name, String category, Long supplierId, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.supplierId = supplierId;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}