package com.example.supplytracker.repository;

//Importing Alert from Entity 
import com.example.supplytracker.entity.Alert;

//Importing JPA Repository provides Build-in CRUD 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Provides CRUD Operations for the Alert Data
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByStatus(String status);
    List<Alert> findByShipmentId(Long shipmentId);
}
