package com.example.supplytracker.service;

import com.example.supplytracker.dto.ShipmentDTO;
import java.util.List;

public interface ShipmentService {
    ShipmentDTO addShipment(ShipmentDTO shipmentDTO);
    List<ShipmentDTO> getAllShipments();
    ShipmentDTO updateShipment(Long id, ShipmentDTO shipmentDTO);
    void deleteShipment(Long id);
}
