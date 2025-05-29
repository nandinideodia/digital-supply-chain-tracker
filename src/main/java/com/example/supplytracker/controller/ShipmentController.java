package com.example.supplytracker.controller;

import com.example.supplytracker.dto.ShipmentDTO;
import com.example.supplytracker.dto.ShipmentDTO.ShipmentStatusUpdateRequest;
import com.example.supplytracker.service.ShipmentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    // Create a new shipment
    @PostMapping
    public ResponseEntity<ShipmentDTO> createShipment(@Valid @RequestBody ShipmentDTO shipmentDTO) {
        ShipmentDTO createdShipment = shipmentService.createShipment(shipmentDTO);
        return ResponseEntity.ok(createdShipment);
    }

    // Get all shipments
    @GetMapping
    public ResponseEntity<List<ShipmentDTO>> getAllShipments() {
        List<ShipmentDTO> shipments = shipmentService.getAllShipments();
        return ResponseEntity.ok(shipments);
    }

    // Get shipment by id
    @GetMapping("/{id}")
    public ResponseEntity<ShipmentDTO> getShipmentById(@PathVariable Long id) {
        ShipmentDTO shipmentDTO = shipmentService.getShipmentById(id);
        return ResponseEntity.ok(shipmentDTO);
    }

    // Update shipment by id
    @PutMapping("/{id}")
    public ResponseEntity<ShipmentDTO> updateShipment(@PathVariable Long id, @RequestBody ShipmentDTO shipmentDTO) {
        ShipmentDTO updatedShipment = shipmentService.updateShipment(id, shipmentDTO);
        return ResponseEntity.ok(updatedShipment);
    }

    // Delete shipment by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }
    
	
//	@PutMapping("/{id}/status")
//	public ResponseEntity<ShipmentDTO> updateShipmentStatus(@PathVariable Long id, @RequestBody ShipmentStatusUpdateRequest statusUpdateRequest) {
//	    ShipmentDTO updatedShipment = shipmentService.updateShipment(id, statusUpdateRequest.getCurrentStatus());
//	    return ResponseEntity.ok(updatedShipment);
//	}
    @PutMapping("/{id}/status")
    public ResponseEntity<ShipmentDTO> updateShipmentStatus(
            @PathVariable Long id,
            @RequestBody ShipmentStatusUpdateRequest statusUpdateRequest) {
        
        ShipmentDTO updatedShipment = shipmentService.updateShipmentStatus(id, statusUpdateRequest.getCurrentStatus());
        return ResponseEntity.ok(updatedShipment);
    }

}