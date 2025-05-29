package com.example.supplytracker.exception;

public class CheckpointLogNotFoundException extends RuntimeException {
    public CheckpointLogNotFoundException(Long id) {
        super("Checkpoint log not found with ID: " + id);
    }
}