package com.example.supplytracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private String role;
   
    public UserDTO() {}

    public UserDTO(String name, String role, String password, String email) {
        this.name = name;
        this.role = role;
        this.password = password;
        this.email = email;
    }

    public String getName() { 
    	return name; 
    }
    public void setName(String name) { 
    	this.name = name;
    }

    public String getRole() { 
    	return role; 
    }
    public void setRole(String role) { 
    	this.role = role; 
    }

    public String getPassword() {
    	return password; 
    }
    public void setPassword(String password) { 
    	this.password = password; 
    }

    public String getEmail() { 
    	return email;
    }
    public void setEmail(String email) { 
    	this.email = email; 
    }
}

