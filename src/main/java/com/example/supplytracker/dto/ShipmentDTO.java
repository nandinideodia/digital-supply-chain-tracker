package com.example.supplytracker.dto;

import com.example.supplytracker.enums.ShipmentStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ShipmentDTO {

    private Long id;

    @NotNull(message = "Item ID cannot be null")
    private Long itemId;

    @NotBlank(message = "From location cannot be empty")
    private String fromLocation;

    @NotBlank(message = "To location cannot be empty")
    private String toLocation;

    @NotNull(message = "Expected delivery date cannot be null")
    @Future(message = "Expected delivery date must be in the future")
    private LocalDateTime expectedDelivery;

    @NotNull(message = "Current status cannot be null")
    private ShipmentStatus currentStatus;

    public static class ShipmentStatusUpdateRequest {

        @NotNull(message = "Current status cannot be null")
        private ShipmentStatus currentStatus;

        public ShipmentStatus getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(ShipmentStatus currentStatus) {
            this.currentStatus = currentStatus;
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public String getFromLocation() { return fromLocation; }
    public void setFromLocation(String fromLocation) { this.fromLocation = fromLocation; }

    public String getToLocation() { return toLocation; }
    public void setToLocation(String toLocation) { this.toLocation = toLocation; }

    public LocalDateTime getExpectedDelivery() { return expectedDelivery; }
    public void setExpectedDelivery(LocalDateTime expectedDelivery) { this.expectedDelivery = expectedDelivery; }

    public ShipmentStatus getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(ShipmentStatus currentStatus) { this.currentStatus = currentStatus; }
}