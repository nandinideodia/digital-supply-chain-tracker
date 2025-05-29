package com.example.supplytracker.exception;

public class InvalidSupplierIdException extends RuntimeException {
    public InvalidSupplierIdException(String string) {
        super("Invalid supplier ID provided: " + string);
    }
}