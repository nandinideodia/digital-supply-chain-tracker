package com.example.supplytracker.entity;

import jakarta.persistence.*;
import com.example.supplytracker.enums.Role;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//@Id and @GeneratedValue will help me generate a primary key id

    private String name;

    @Column(unique = true)// it causes the email to be unique for each user
    private String email;

    private String password;

    @Enumerated(EnumType.STRING) // maps the roles present in enums to this as a string 
    private Role role;

    public User() {}

    public User(Long id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() { 
    	return id; 
    }
    public void setId(Long id) { 
    	this.id = id; 
    }

    public String getName() { 
    	return name;
    }
    public void setName(String name) { 
    	this.name = name;
    }

    public String getEmail() {
    	return email; 
    }
    public void setEmail(String email) { 
    	this.email = email; 
    }

    public String getPassword() {
    	return password; 
    }
    public void setPassword(String password) { 
    	this.password = password;
    }

    public Role getRole() { 
    	return role; 
    }
    public void setRole(Role role) { 
    	this.role = role; 
    }
}

