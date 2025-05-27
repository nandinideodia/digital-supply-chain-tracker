package com.example.supplytracker.repository;

//Importing Report from Entity 
import com.example.supplytracker.entity.Report;

//Importing JPA Repository provides Build-in CRUD 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Provides CRUD Operations for the Report Data
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByGeneratedBy(String username);
    List<Report> findByShipmentId(Long shipmentId);
}
