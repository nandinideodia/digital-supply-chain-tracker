package com.example.supplytracker.exception;

public class InvalidShipmentReferenceException extends RuntimeException {
    public InvalidShipmentReferenceException(Long shipmentId) {
        super("Shipment reference invalid or missing: " + shipmentId);
    }
}