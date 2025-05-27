package com.example.supplytracker.repository;

//Importing User from Entity 
import com.example.supplytracker.entity.User;

//Importing JPA Repository provides Build-in CRUD 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

//Provides CRUD Operations for the User Data
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
