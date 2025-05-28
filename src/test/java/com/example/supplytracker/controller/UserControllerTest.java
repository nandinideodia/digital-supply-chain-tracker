package com.example.supplytracker.controller;

import com.example.supplytracker.dto.UserDTO;
import com.example.supplytracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    // Test case for creating a user successfully
    @Test
    void testCreateUser_Success() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.setEmail("john@example.com");
        userDTO.setPassword("password123");

        when(userService.createUser(userDTO)).thenReturn(userDTO);

        UserDTO response = userController.createUser(userDTO);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        assertEquals("john@example.com", response.getEmail());
    }

    //Test case for retrieving all users 
    @Test
    void testGetAllUsers() {
        UserDTO user1 = new UserDTO();
        user1.setName("Alice");
        user1.setEmail("alice@example.com");

        UserDTO user2 = new UserDTO();
        user2.setName("Bob");
        user2.setEmail("bob@example.com");

        List<UserDTO> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        List<UserDTO> response = userController.getAllUsers();

        assertEquals(2, response.size());
        assertTrue(response.contains(user1));
        assertTrue(response.contains(user2));
    }

    /**
     * Test case for retrieving a user by ID successfully.
     */
    @Test
    void testGetUserById_Success() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Charlie");
        userDTO.setEmail("charlie@example.com");

        when(userService.getUserById(1L)).thenReturn(userDTO);

        UserDTO response = userController.getUserById(1L);

        assertNotNull(response);
        assertEquals("Charlie", response.getName());
        assertEquals("charlie@example.com", response.getEmail());
    }

    //Test case for updating a user  
    @Test
    void testUpdateUser_Success() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Dave");
        userDTO.setEmail("dave@example.com");

        when(userService.updateUser(1L, userDTO)).thenReturn(userDTO);

        UserDTO response = userController.updateUser(1L, userDTO);

        assertNotNull(response);
        assertEquals("Dave", response.getName());
        assertEquals("dave@example.com", response.getEmail());
    }

    // Test case for deleting a user successfully
     
    @Test
    void testDeleteUser_Success() {
        when(userService.deleteUser(1L)).thenReturn(true);

        String response = userController.deleteUser(1L);

        assertEquals("Deleted successfully", response);
    }

    // Test case for handling user deletion failure
     
    @Test
    void testDeleteUser_NotFound() {
        when(userService.deleteUser(99L)).thenReturn(false);

        String response = userController.deleteUser(99L);

        assertEquals("User not found", response);
    }
}