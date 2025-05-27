package com.example.supplytracker.repository;

// Importing Item from Entity 
import com.example.supplytracker.entity.Item;

// Importing JPA Repository provides Build-in CRUD 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Provides CRUD Operations for the Item Data
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByShipmentId(Long shipmentId);
}
