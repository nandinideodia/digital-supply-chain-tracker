package com.example.supplytracker.exception;

public class InvalidShipmentStatusException extends RuntimeException {
    public InvalidShipmentStatusException(String status) {
        super("Invalid shipment status: " + status);
    }
}