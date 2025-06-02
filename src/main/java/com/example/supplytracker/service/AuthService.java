package com.example.supplytracker.service;

import com.example.supplytracker.dto.UserDTO;

public interface AuthService {
    String register(UserDTO userDTO);
    String login(UserDTO userDTO);
}
