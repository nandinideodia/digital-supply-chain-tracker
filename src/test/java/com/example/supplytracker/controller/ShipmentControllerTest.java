package com.example.supplytracker.controller;

import com.example.supplytracker.dto.ShipmentDTO;
import com.example.supplytracker.enums.ShipmentStatus;
import com.example.supplytracker.service.ShipmentService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipmentControllerTest {

    @Mock
    private ShipmentService shipmentService;

    @InjectMocks
    private ShipmentController shipmentController;

    @Test
    void testCreateShipment_Success() {
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        shipmentDTO.setId(1L);
        shipmentDTO.setFromLocation("LocA");
        shipmentDTO.setToLocation("LocB");
        shipmentDTO.setExpectedDelivery(LocalDateTime.now());
        shipmentDTO.setCurrentStatus(ShipmentStatus.In_transit);  // use valid enum here
        shipmentDTO.setItemId(100L);

        when(shipmentService.createShipment(any(ShipmentDTO.class))).thenReturn(shipmentDTO);

        ResponseEntity<ShipmentDTO> response = shipmentController.createShipment(shipmentDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(shipmentDTO, response.getBody());
        verify(shipmentService).createShipment(shipmentDTO);
    }

    @Test
    void testGetAllShipments() {
        ShipmentDTO shipment1 = new ShipmentDTO();
        shipment1.setCurrentStatus(ShipmentStatus.Delivered);  // valid enum
        ShipmentDTO shipment2 = new ShipmentDTO();
        shipment2.setCurrentStatus(ShipmentStatus.In_transit); // replaced PENDING with In_transit

        List<ShipmentDTO> shipmentList = Arrays.asList(shipment1, shipment2);

        when(shipmentService.getAllShipments()).thenReturn(shipmentList);

        ResponseEntity<List<ShipmentDTO>> response = shipmentController.getAllShipments();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(shipmentService).getAllShipments();
    }

    @Test
    void testGetShipmentById_Success() {
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        shipmentDTO.setId(1L);
        shipmentDTO.setCurrentStatus(ShipmentStatus.In_transit);  // replaced PENDING with In_transit

        when(shipmentService.getShipmentById(1L)).thenReturn(shipmentDTO);

        ResponseEntity<ShipmentDTO> response = shipmentController.getShipmentById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(shipmentDTO, response.getBody());
        verify(shipmentService).getShipmentById(1L);
    }

    @Test
    void testGetShipmentById_NotFound() {
        when(shipmentService.getShipmentById(99L)).thenThrow(new EntityNotFoundException("Shipment not found"));

        try {
            shipmentController.getShipmentById(99L);
            fail("Expected EntityNotFoundException");
        } catch (EntityNotFoundException ex) {
            assertEquals("Shipment not found", ex.getMessage());
        }

        verify(shipmentService).getShipmentById(99L);
    }

    @Test
    void testUpdateShipment_Success() {
        Long id = 1L;
        ShipmentDTO inputDTO = new ShipmentDTO();
        inputDTO.setFromLocation("LocX");
        inputDTO.setToLocation("LocY");
        inputDTO.setItemId(200L);
        inputDTO.setCurrentStatus(ShipmentStatus.In_transit);  // valid enum

        ShipmentDTO updatedDTO = new ShipmentDTO();
        updatedDTO.setId(id);
        updatedDTO.setFromLocation("LocX");
        updatedDTO.setToLocation("LocY");
        updatedDTO.setItemId(200L);
        updatedDTO.setCurrentStatus(ShipmentStatus.In_transit);

        when(shipmentService.updateShipment(eq(id), any(ShipmentDTO.class))).thenReturn(updatedDTO);

        ResponseEntity<ShipmentDTO> response = shipmentController.updateShipment(id, inputDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedDTO, response.getBody());
        verify(shipmentService).updateShipment(id, inputDTO);
    }

    @Test
    void testDeleteShipment() {
        Long id = 1L;

        doNothing().when(shipmentService).deleteShipment(id);

        ResponseEntity<Void> response = shipmentController.deleteShipment(id);

        assertEquals(204, response.getStatusCodeValue());
        verify(shipmentService).deleteShipment(id);
    }
}