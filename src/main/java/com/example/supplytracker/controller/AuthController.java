package com.example.supplytracker.controller;

import com.example.supplytracker.dto.UserDTO;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Registration
    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // For now, no encryption
        user.setRole(userDTO.getRole());

        userRepository.save(user);
        return "User registered successfully";
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail())
                .filter(u -> u.getPassword().equals(userDTO.getPassword()))
                .map(u -> "Login successful as " + u.getRole())
                .orElse("Invalid credentials");
    }
}
