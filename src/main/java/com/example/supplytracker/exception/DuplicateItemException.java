package com.example.supplytracker.exception;

public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException(String name) {
        super("Item already exists with name: " + name);
    }
}