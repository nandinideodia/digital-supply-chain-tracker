package com.example.supplytracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.example.supplytracker.enums.ShipmentStatus;

@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Item entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private String fromLocation;
    private String toLocation;

    private LocalDateTime expectedDelivery;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus currentStatus;

    // Link to User entity as assigned transporter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_transporter_id")
    private User assignedTransporter;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    // Convenience getter/setter for itemId

    public Long getItemId() {
        return (item != null) ? item.getId() : null;
    }

    public void setItemId(Long itemId) {
        if (this.item == null) {
            this.item = new Item();
        }
        this.item.setId(itemId);
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

    public LocalDateTime getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDateTime expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public ShipmentStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(ShipmentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public User getAssignedTransporter() {
        return assignedTransporter;
    }

    public void setAssignedTransporter(User assignedTransporter) {
        this.assignedTransporter = assignedTransporter;
    }
}
