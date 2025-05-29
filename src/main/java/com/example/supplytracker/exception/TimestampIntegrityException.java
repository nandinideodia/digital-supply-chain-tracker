package com.example.supplytracker.exception;

public class TimestampIntegrityException extends RuntimeException {
    public TimestampIntegrityException() {
        super("Checkpoint timestamp is missing or incorrectly formatted.");
    }
}