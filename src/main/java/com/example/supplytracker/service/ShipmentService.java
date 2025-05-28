package com.example.supplytracker.service;

import com.example.supplytracker.dto.ShipmentDTO;
import com.example.supplytracker.enums.ShipmentStatus;

import java.util.List;

public interface ShipmentService {
    ShipmentDTO createShipment(ShipmentDTO shipmentDTO);
    List<ShipmentDTO> getAllShipments();
    ShipmentDTO getShipmentById(Long id);
    ShipmentDTO updateShipment(Long id, ShipmentDTO shipmentDTO);
    void deleteShipment(Long id);
    
    ShipmentDTO updateShipmentStatus(Long id, ShipmentStatus newStatus);  // ✅ renamed
}
