package com.example.supplytracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private String fromLocation;

    @Column(nullable = false)
    private String toLocation;

    @Column(nullable = false)
    private LocalDate expectedDelivery;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status currentStatus;

    public enum Status {
        CREATED,
        IN_TRANSIT,
        DELIVERED,
        DELAYED
    }

    // Default constructor
    public Shipment() {}

    // All argument constructor
    public Shipment(Long itemId, String fromLocation, String toLocation, LocalDate expectedDelivery, Status currentStatus) {
        this.itemId = itemId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.expectedDelivery = expectedDelivery;
        this.currentStatus = currentStatus;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }
}
