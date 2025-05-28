package com.example.supplytracker.controller;

import com.example.supplytracker.dto.UserDTO;
import com.example.supplytracker.entity.Role;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    // Test case for registering a new user successfully
    @Test
    void testRegister_Success() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.setEmail("john@example.com");
        userDTO.setPassword("password123");
        userDTO.setRole(Role.Admin); // Updated role

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());

        String response = authController.register(userDTO);
        assertEquals("User registered successfully", response);
        verify(userRepository).save(any(User.class));
    }

    // Test case for when registering fails due to an existing email 
    @Test
    void testRegister_EmailExists() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("jane@example.com");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(new User()));

        String response = authController.register(userDTO);
        assertEquals("Email already exists", response);
    }

    // Test case of successful login with valid credentials
    @Test
    void testLogin_Success() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("john@example.com");
        userDTO.setPassword("password123");

        User existingUser = new User();
        existingUser.setEmail("john@example.com");
        existingUser.setPassword("password123");
        existingUser.setRole(Role.Admin); // Updated role

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(existingUser));

        String response = authController.login(userDTO);
        assertEquals("Login successful as Admin", response);
    }

    // Test case for when login fails due to wrong credentials
    @Test
    void testLogin_InvalidCredentials() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("john@example.com");
        userDTO.setPassword("wrongpass");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());

        String response = authController.login(userDTO);
        assertEquals("Invalid credentials", response);
    }
}