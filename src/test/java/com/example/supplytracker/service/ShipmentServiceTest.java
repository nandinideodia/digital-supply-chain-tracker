package com.example.supplytracker.service;

import com.example.supplytracker.dto.ShipmentDTO;
import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.Shipment;
import com.example.supplytracker.enums.ShipmentStatus;
import com.example.supplytracker.exception.ShipmentNotFoundException;
import com.example.supplytracker.repository.ItemRepository;
import com.example.supplytracker.repository.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceTest {

    @Mock private ShipmentRepository shipmentRepository;
    @Mock private ItemRepository itemRepository;

    @InjectMocks private ShipmentServiceImpl shipmentService;

    private Shipment shipment;
    private Item item;
    private ShipmentDTO shipmentDTO;

    @BeforeEach
    void setUp() {
        item = createTestItem();
        shipment = createTestShipment();
        shipmentDTO = createTestShipmentDTO();
    }

    private Item createTestItem() {
        Item item = new Item();
        item.setId(1L);
        return item;
    }

    private Shipment createTestShipment() {
        Shipment shipment = new Shipment();
        shipment.setId(1L);
        shipment.setItem(item);
        shipment.setFromLocation("Warehouse A");
        shipment.setToLocation("Warehouse B");
        shipment.setExpectedDelivery(LocalDateTime.now().plusDays(3));
        shipment.setCurrentStatus(ShipmentStatus.In_transit);
        return shipment;
    }

    private ShipmentDTO createTestShipmentDTO() {
        ShipmentDTO dto = new ShipmentDTO();
        dto.setId(1L);
        dto.setItemId(1L);
        dto.setFromLocation("Warehouse A");
        dto.setToLocation("Warehouse B");
        dto.setExpectedDelivery(LocalDateTime.now().plusDays(3));
        dto.setCurrentStatus(ShipmentStatus.In_transit);
        return dto;
    }

    @Test
    void testCreateShipment_Success() {
        when(itemRepository.findById(shipmentDTO.getItemId())).thenReturn(Optional.of(item));
        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        ShipmentDTO result = shipmentService.createShipment(shipmentDTO);

        assertNotNull(result);
        assertEquals(ShipmentStatus.In_transit, result.getCurrentStatus());
        verify(shipmentRepository).save(any(Shipment.class));
        verify(itemRepository, times(2)).findById(1L);
    }

    @Test
    void testGetShipmentById_Success() {
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));

        ShipmentDTO result = shipmentService.getShipmentById(1L);

        assertNotNull(result);
        assertEquals("Warehouse A", result.getFromLocation());
        assertEquals("Warehouse B", result.getToLocation());
        assertEquals(ShipmentStatus.In_transit, result.getCurrentStatus());
        verify(shipmentRepository).findById(1L);
    }

    @Test
    void testGetShipmentById_NotFound() {
        when(shipmentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ShipmentNotFoundException.class, () -> shipmentService.updateShipment(1L, shipmentDTO));
        verify(shipmentRepository).findById(1L);
    }

    @Test
    void testUpdateShipment_Success() {
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        ShipmentDTO updatedDTO = createTestShipmentDTO();
        updatedDTO.setFromLocation("Updated Location");
        updatedDTO.setToLocation("Updated Destination");
        updatedDTO.setExpectedDelivery(LocalDateTime.now().plusDays(5));
        updatedDTO.setCurrentStatus(ShipmentStatus.Delivered);

        ShipmentDTO result = shipmentService.updateShipment(1L, updatedDTO);

        assertNotNull(result);
        assertEquals("Updated Location", result.getFromLocation());
        assertEquals("Updated Destination", result.getToLocation());
        assertEquals(ShipmentStatus.Delivered, result.getCurrentStatus());
        verify(shipmentRepository).save(any(Shipment.class));
    }

    @Test
    void testUpdateShipment_NotFound() {
        when(shipmentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ShipmentNotFoundException.class, () -> shipmentService.updateShipment(1L, shipmentDTO));
        verify(shipmentRepository).findById(1L);
    }

    @Test
    void testDeleteShipment_Success() {
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));

        shipmentService.deleteShipment(1L);

        verify(shipmentRepository).delete(shipment);
        verify(shipmentRepository).findById(1L);
    }

    @Test
    void testDeleteShipment_NotFound() {
        when(shipmentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ShipmentNotFoundException.class, () -> shipmentService.deleteShipment(1L));
        verify(shipmentRepository).findById(1L);
    }


    @Test
    void testUpdateShipmentStatus_Success() {
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        ShipmentDTO result = shipmentService.updateShipmentStatus(1L, ShipmentStatus.Delivered);

        assertNotNull(result);
        assertEquals(ShipmentStatus.Delivered, result.getCurrentStatus());
        verify(shipmentRepository).save(any(Shipment.class));
        verify(shipmentRepository).findById(1L);
    }
}