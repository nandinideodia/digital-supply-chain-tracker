package com.example.supplytracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class CheckpointLogDTO {

    private Long id;

    @NotNull(message = "Shipment ID cannot be null")
    private Long shipmentId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotBlank(message = "Message cannot be empty")
    private String message;

    @NotNull(message = "Timestamp cannot be null")
    @PastOrPresent(message = "Timestamp cannot be in the future")
    private LocalDateTime timestamp;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getShipmentId() { return shipmentId; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}