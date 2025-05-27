package com.example.supplytracker.repository;

//Importing Checkpoint from Entity 
import com.example.supplytracker.entity.CheckpointLog;

//Importing JPA Repository provides Build-in CRUD 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Provides CRUD Operations for the Checkpoint Data
public interface CheckpointRepository extends JpaRepository<CheckpointLog, Long> {
    List<CheckpointLog> findByShipment(Long shipmentId);
}
