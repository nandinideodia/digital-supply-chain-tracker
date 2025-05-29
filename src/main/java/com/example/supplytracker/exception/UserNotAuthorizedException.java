package com.example.supplytracker.exception;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(Long userId) {
        super("User with ID " + userId + " is not authorized to update this checkpoint.");
    }
}