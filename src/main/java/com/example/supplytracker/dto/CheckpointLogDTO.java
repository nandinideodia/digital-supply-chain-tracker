package com.example.supplytracker.dto;

import java.time.LocalDateTime;

public class CheckpointLogDTO {
    private Long id;
    private Long shipmentId;
    private Long userId;
    private String message;
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
