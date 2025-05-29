package com.example.supplytracker.exception;
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long long1) {
        super("Item not found in shipment with ID: " + long1);
    }
}