package com.example.supplytracker.service;

import com.example.supplytracker.dto.DeliveryPerformanceDTO;
import com.example.supplytracker.entity.Shipment;
import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReportService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<DeliveryPerformanceDTO> getPerformancePerSupplier() {
        List<Shipment> shipments = shipmentRepository.findAll();
        Map<String, DeliveryPerformanceDTO> result = new HashMap<>();

        for (Shipment s : shipments) {
            Item item = s.getItem();
            if (item == null || item.getSupplier() == null ) continue;

            String supplierName = item.getSupplier().getName();
            LocalDateTime expected = s.getExpectedDelivery();
            LocalDateTime deliveredAt = null;

            if (s.getCurrentStatus().name().equalsIgnoreCase("Delivered")) {
                deliveredAt = expected.minusHours(1); // fake logic: assume it arrived 1h early
            }

            result.putIfAbsent(supplierName, new DeliveryPerformanceDTO(supplierName, 0, 0, 0));
            DeliveryPerformanceDTO dto = result.get(supplierName);
            dto.setTotalShipments(dto.getTotalShipments() + 1);

            if (deliveredAt != null && !deliveredAt.isAfter(expected)) {
                dto.setOnTimeDeliveries(dto.getOnTimeDeliveries() + 1);
            } else {
                dto.setDelayedDeliveries(dto.getDelayedDeliveries() + 1);
            }
        }

        return new ArrayList<>(result.values());
    }

    public List<Shipment> getDelayedShipments() {
        List<Shipment> delayed = new ArrayList<>();
        for (Shipment s : shipmentRepository.findAll()) {
            if (s.getExpectedDelivery() != null &&
                s.getCurrentStatus() != null &&
                s.getCurrentStatus().name().equalsIgnoreCase("Delayed")) {
                delayed.add(s);
            }
        }
        return delayed;
    }
}