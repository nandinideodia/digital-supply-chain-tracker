package com.example.supplytracker.service;

import com.example.supplytracker.dto.AlertDTO;
import com.example.supplytracker.entity.Alert;
import com.example.supplytracker.entity.Shipment;
import com.example.supplytracker.enums.AlertType;
import com.example.supplytracker.repository.AlertRepository;
import com.example.supplytracker.repository.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertService {
    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<AlertDTO> getAllAlerts() {
        return alertRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public AlertDTO createAlert(AlertDTO alertDTO) {
        Shipment shipment = shipmentRepository.findById(alertDTO.getShipmentId())
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found with ID: " + alertDTO.getShipmentId()));

        Alert alert = new Alert();
        alert.setShipment(shipment);
        alert.setType(AlertType.valueOf(alertDTO.getType()));
        alert.setMessage(alertDTO.getMessage());
        alert.setCreatedOn(LocalDateTime.now());
        alert.setResolved(false);

        Alert savedAlert = alertRepository.save(alert);
        return convertToDTO(savedAlert);
    }

    public AlertDTO resolveAlert(Long id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alert not found with ID: " + id));

        alert.setResolved(true);
        return convertToDTO(alertRepository.save(alert));
    }

    private AlertDTO convertToDTO(Alert alert) {
        AlertDTO dto = new AlertDTO();
        dto.setId(alert.getId());
        dto.setShipmentId(alert.getShipment().getId());
        dto.setType(alert.getType().name());
        dto.setMessage(alert.getMessage());
        dto.setCreatedOn(alert.getCreatedOn());
        dto.setResolved(alert.getResolved());
        return dto;
    }
}