package com.example.supplytracker.service;

import com.example.supplytracker.dto.UserDTO;
import com.example.supplytracker.entity.User;
import java.util.List;

public interface UserService {
    User registerUser(UserDTO dto);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, UserDTO dto);
    void deleteUser(Long id);
}