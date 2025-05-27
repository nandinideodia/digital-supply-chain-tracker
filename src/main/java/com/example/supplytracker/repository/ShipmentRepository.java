package com.example.supplytracker.repository;

//Importing Shipment from Entity 
import com.example.supplytracker.entity.Shipment;

//Importing JPA Repository provides Build-in CRUD 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Provides CRUD Operations for the Shipment Data
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
//    List<Shipment> findByStatus(String status);
//    List<Shipment> findByUserId(Long userId);
}
