package com.example.supplytracker.exception;

public class ShipmentAlreadyDeliveredException extends RuntimeException {
    public ShipmentAlreadyDeliveredException(Long id) {
        super("Shipment with ID " + id + " has already been delivered and cannot be modified.");
    }
}