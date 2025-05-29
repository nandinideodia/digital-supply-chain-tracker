package com.example.supplytracker.exception;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String role) {
        super("Invalid role provided: " + role);
    }
}
