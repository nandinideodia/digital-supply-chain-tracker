package com.example.supplytracker.exception;

public class AuthenticationFailureException extends RuntimeException {
    public AuthenticationFailureException() {
        super("Authentication failed: Incorrect credentials.");
    }
}