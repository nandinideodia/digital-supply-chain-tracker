package com.example.supplytracker.service;

import com.example.supplytracker.dto.ShipmentDTO;

import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.Shipment;
import com.example.supplytracker.enums.ShipmentStatus;
import com.example.supplytracker.repository.ItemRepository;
import com.example.supplytracker.repository.ShipmentRepository;
import com.example.supplytracker.service.ShipmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supplytracker.exception.ShipmentNotFoundException;
import com.example.supplytracker.exception.InvalidShipmentStatusException;
import com.example.supplytracker.exception.ItemNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ItemRepository itemRepository;  // <-- Added

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository, ItemRepository itemRepository) {
        this.shipmentRepository = shipmentRepository;
        this.itemRepository = itemRepository;  // <-- Added
    }

    @Override
    public ShipmentDTO createShipment(ShipmentDTO shipmentDTO) {
        Item item = itemRepository.findById(shipmentDTO.getItemId())
            .orElseThrow(() -> new IllegalArgumentException("Item not found for ID: " + shipmentDTO.getItemId()));

        Shipment shipment = dtoToEntity(shipmentDTO);
        shipment.setItem(item); // Ensure item is set before saving

        Shipment savedShipment = shipmentRepository.save(shipment);
        return entityToDto(savedShipment);
    }
    
    

    @Override
    public List<ShipmentDTO> getAllShipments() {
        return shipmentRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShipmentDTO getShipmentById(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
            .orElseThrow(() -> new ShipmentNotFoundException(id));
        return entityToDto(shipment);
    }

    @Override
    public ShipmentDTO updateShipment(Long id, ShipmentDTO shipmentDTO) {
        Shipment existingShipment = shipmentRepository.findById(id)
            .orElseThrow(() -> new ShipmentNotFoundException(id));

        Item item = itemRepository.findById(shipmentDTO.getItemId())
            .orElseThrow(() -> new ItemNotFoundException(shipmentDTO.getItemId()));

        if (shipmentDTO.getCurrentStatus() == null) {
            throw new InvalidShipmentStatusException("Status cannot be null");
        }

        existingShipment.setItem(item);
        existingShipment.setFromLocation(shipmentDTO.getFromLocation());
        existingShipment.setToLocation(shipmentDTO.getToLocation());
        existingShipment.setExpectedDelivery(shipmentDTO.getExpectedDelivery());
        existingShipment.setCurrentStatus(shipmentDTO.getCurrentStatus());

        return entityToDto(shipmentRepository.save(existingShipment));
    }

    @Override
    public void deleteShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new ShipmentNotFoundException(id));
        shipmentRepository.delete(shipment);
    }

 
    public ShipmentDTO updateShipment(Long id, ShipmentStatus newStatus) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found with id " + id));

        shipment.setCurrentStatus(newStatus);
        Shipment updatedShipment = shipmentRepository.save(shipment);

        return entityToDto(updatedShipment);
    }

    // Helper method to convert DTO to Entity
    private Shipment dtoToEntity(ShipmentDTO dto) {
        Shipment shipment = new Shipment();
        shipment.setId(dto.getId());

        // Fetch Item entity for the itemId
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id " + dto.getItemId()));
        shipment.setItem(item);

        shipment.setFromLocation(dto.getFromLocation());
        shipment.setToLocation(dto.getToLocation());
        shipment.setExpectedDelivery(dto.getExpectedDelivery());
        shipment.setCurrentStatus(dto.getCurrentStatus());
        return shipment;
    }

    // Helper method to convert Entity to DTO
    private ShipmentDTO entityToDto(Shipment shipment) {
        ShipmentDTO dto = new ShipmentDTO();
        dto.setId(shipment.getId());

        // Extract itemId from the Item entity
        if (shipment.getItem() != null) {
            dto.setItemId(shipment.getItem().getId());
        }

        dto.setFromLocation(shipment.getFromLocation());
        dto.setToLocation(shipment.getToLocation());
        dto.setExpectedDelivery(shipment.getExpectedDelivery());
        dto.setCurrentStatus(shipment.getCurrentStatus());
        return dto;
    }
    
    public ShipmentDTO updateShipmentStatus(Long id, ShipmentStatus newStatus) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shipment not found with ID: " + id));

        shipment.setCurrentStatus(newStatus);
        Shipment updatedShipment = shipmentRepository.save(shipment);

        return mapToDto(updatedShipment); // Or build DTO manually if no mapper
    }
    
    private ShipmentDTO mapToDto(Shipment shipment) {
        ShipmentDTO dto = new ShipmentDTO();
        dto.setId(shipment.getId());

        // Extract itemId from the Item entity
        if (shipment.getItem() != null) {
            dto.setItemId(shipment.getItem().getId());
        }

        dto.setFromLocation(shipment.getFromLocation());
        dto.setToLocation(shipment.getToLocation());
        dto.setExpectedDelivery(shipment.getExpectedDelivery());
        dto.setCurrentStatus(shipment.getCurrentStatus());

        return dto;
    }
    
}