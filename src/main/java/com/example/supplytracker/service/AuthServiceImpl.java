package com.example.supplytracker.service;

import com.example.supplytracker.dto.UserDTO;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String register(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // Add encryption if needed
        user.setRole(userDTO.getRole());

        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String login(UserDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail())
                .filter(u -> u.getPassword().equals(userDTO.getPassword()))
                .map(u -> "Login successful as " + u.getRole())
                .orElse("Invalid credentials");
    }
}
