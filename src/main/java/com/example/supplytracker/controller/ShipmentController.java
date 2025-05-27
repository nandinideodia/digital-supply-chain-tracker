package com.example.supplytracker.controller;

import com.example.supplytracker.dto.ShipmentDTO;
import com.example.supplytracker.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                                          // it indicates that class handles HTTP request 
@RequestMapping("/api/shipments")						// it define the base url 
public class ShipmentController {

    @Autowired											// it enables to perform ShipmentService in this class
    private ShipmentService shipmentService;

    //  Add Shipment
    @PostMapping
    public ShipmentDTO addShipment(@RequestBody ShipmentDTO shipmentDTO) {
        return shipmentService.addShipment(shipmentDTO);
    }

    //  View All Shipments
    @GetMapping
    public List<ShipmentDTO> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    //  Update Shipment
    @PutMapping("/{id}")
    public ShipmentDTO updateShipment(@PathVariable Long id, @RequestBody ShipmentDTO shipmentDTO) {
        return shipmentService.updateShipment(id, shipmentDTO);
    }

    //  Delete Shipment
    @DeleteMapping("/{id}")
    public void deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
    }
}