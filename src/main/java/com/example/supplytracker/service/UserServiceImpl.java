package com.example.supplytracker.service;

import com.example.supplytracker.dto.UserDTO;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.supplytracker.exception.UserNotFoundException;
import com.example.supplytracker.exception.EmailAlreadyExistsException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ControllerAdvice
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    // Create
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException(userDTO.getEmail());
        }

        User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getRole());
        User saved = userRepository.save(user);
        userDTO.setId(saved.getId());
        return userDTO;
    }

    // Read all
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Read by ID
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    // Update
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> existingOpt = userRepository.findById(id);
        if (existingOpt.isPresent()) {
            User user = existingOpt.get();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());
            return convertToDTO(userRepository.save(user));
        }
        return null;
    }

    // Delete
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Utility: Entity to DTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
    }   
    
}
