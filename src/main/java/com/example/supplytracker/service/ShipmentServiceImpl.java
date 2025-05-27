package com.example.supplytracker.service;

import com.example.supplytracker.dto.ShipmentDTO;
import com.example.supplytracker.entity.Shipment;
import com.example.supplytracker.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired                                 // enables Repository related operations in service class
    private ShipmentRepository shipmentRepository;

    @Override
    public ShipmentDTO addShipment(ShipmentDTO dto) {
        Shipment shipment = new Shipment(
            dto.getItemId(),
            dto.getFromLocation(),
            dto.getToLocation(),
            dto.getExpectedDelivery(),
            dto.getCurrentStatus()
        );
        return mapToDTO(shipmentRepository.save(shipment));  //convert the save entity into dto and return it.
    }

    @Override
    public List<ShipmentDTO> getAllShipments() {
        return shipmentRepository.findAll().stream()
                .map(this::mapToDTO)                  //java streams are used to convert the entities to dto
                .collect(Collectors.toList());            //collect and return the dto
    }

    @Override
    public ShipmentDTO updateShipment(Long id, ShipmentDTO dto) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));  //if not found give exception

        shipment.setItemId(dto.getItemId());
        shipment.setFromLocation(dto.getFromLocation());
        shipment.setToLocation(dto.getToLocation());
        shipment.setExpectedDelivery(dto.getExpectedDelivery());
        shipment.setCurrentStatus(dto.getCurrentStatus());           //update the respective dtos

        return mapToDTO(shipmentRepository.save(shipment));				// save the updated dtos
    }

    @Override
    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }

    private ShipmentDTO mapToDTO(Shipment shipment) {					//method to convert shipment entity to dto
        ShipmentDTO dto = new ShipmentDTO();
        dto.setId(shipment.getId());
        dto.setItemId(shipment.getItemId());
        dto.setFromLocation(shipment.getFromLocation());
        dto.setToLocation(shipment.getToLocation());
        dto.setExpectedDelivery(shipment.getExpectedDelivery());
        dto.setCurrentStatus(shipment.getCurrentStatus());
        return dto;
    }
}