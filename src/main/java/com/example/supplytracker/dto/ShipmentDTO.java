package com.example.supplytracker.dto;

import com.example.supplytracker.enums.ShipmentStatus;
import java.time.LocalDateTime;

import com.example.supplytracker.enums.ShipmentStatus;

public class ShipmentDTO {

    private Long id;
    private Long itemId;
    private String fromLocation;
    private String toLocation;
    private LocalDateTime expectedDelivery;
    private ShipmentStatus currentStatus;
    
    public static class ShipmentStatusUpdateRequest {

        private ShipmentStatus currentStatus;

        public ShipmentStatus getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(ShipmentStatus currentStatus) {
            this.currentStatus = currentStatus;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
