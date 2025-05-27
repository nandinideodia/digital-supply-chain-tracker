package com.example.supplytracker.controller;

import com.example.supplytracker.dto.ShipmentDTO;
import com.example.supplytracker.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    // 1. Add Shipment
    @PostMapping
    public ShipmentDTO addShipment(@RequestBody ShipmentDTO shipmentDTO) {
        return shipmentService.addShipment(shipmentDTO);
    }

    // 2. View All Shipments
    @GetMapping
    public List<ShipmentDTO> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    // 3. Update Shipment
    @PutMapping("/{id}")
    public ShipmentDTO updateShipment(@PathVariable Long id, @RequestBody ShipmentDTO shipmentDTO) {
        return shipmentService.updateShipment(id, shipmentDTO);
    }

    // 4. Delete Shipment
    @DeleteMapping("/{id}")
    public void deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
    }
}