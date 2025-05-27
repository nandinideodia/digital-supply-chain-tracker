package com.example.supplytracker.service;

import com.example.supplytracker.dto.UserDTO;
import com.example.supplytracker.entity.User;
import java.util.List;
// the service interface defines all the user related operations the functions implementations are in the the sub package  
public interface UserService {
    User registerUser(UserDTO userDTO); // to register a user
    List<User> getAllUsers(); //to get all the users
    void updateUserRole(Long userId, String newRole); //user roles are updated using this
}