package com.example.supplytracker.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	private String name;
	private String category;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private User supplier;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
	
    public Item() {
    }

    public Item(String name, String category, User supplier, LocalDateTime createdDate) {
        this.name = name;
        this.category = category;
        this.supplier = supplier;
        this.createdDate = createdDate;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public User getSupplier() {
		return supplier;
	}

	public void setSupplier(User supplier) {
		this.supplier = supplier;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
}
