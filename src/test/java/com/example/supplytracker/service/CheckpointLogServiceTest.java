package com.example.supplytracker.service;

import com.example.supplytracker.dto.CheckpointLogDTO;
import com.example.supplytracker.entity.CheckpointLog;
import com.example.supplytracker.entity.Shipment;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.exception.CheckpointLogNotFoundException;
import com.example.supplytracker.exception.InvalidShipmentReferenceException;
import com.example.supplytracker.exception.UserNotAuthorizedException;
import com.example.supplytracker.repository.CheckpointLogRepository;
import com.example.supplytracker.repository.ShipmentRepository;
import com.example.supplytracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckpointLogServiceTest {

    @Mock private CheckpointLogRepository logRepository;
    @Mock private ShipmentRepository shipmentRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks private CheckpointLogServiceImpl checkpointLogService;

    private CheckpointLog log;
    private Shipment shipment;
    private User user;
    private CheckpointLogDTO checkpointLogDTO;

    @BeforeEach
    void setUp() {
        shipment = new Shipment();
        shipment.setId(1L);

        user = new User();
        user.setId(1L);

        log = new CheckpointLog();
        log.setId(1L);
        log.setMessage("Checkpoint reached");
        log.setTimestamp(LocalDateTime.now());
        log.setShipment(shipment);
        log.setUser(user);

        checkpointLogDTO = new CheckpointLogDTO();
        checkpointLogDTO.setId(1L);
        checkpointLogDTO.setMessage("Checkpoint reached");
        checkpointLogDTO.setTimestamp(LocalDateTime.now());
        checkpointLogDTO.setShipmentId(1L);
        checkpointLogDTO.setUserId(1L);
    }

    @Test
    void testGetLogById_Success() {
        when(logRepository.findById(1L)).thenReturn(Optional.of(log));

        CheckpointLogDTO result = checkpointLogService.getLogById(1L);

        assertNotNull(result);
        assertEquals("Checkpoint reached", result.getMessage());
        verify(logRepository).findById(1L);
    }

    @Test
    void testGetLogById_NotFound() {
        when(logRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CheckpointLogNotFoundException.class, () -> checkpointLogService.getLogById(1L));
        verify(logRepository).findById(1L);
    }

    @Test
    void testAddLog_Success() {
        when(shipmentRepository.findById(checkpointLogDTO.getShipmentId())).thenReturn(Optional.of(shipment));
        when(userRepository.findById(checkpointLogDTO.getUserId())).thenReturn(Optional.of(user));
        when(logRepository.save(any(CheckpointLog.class))).thenReturn(log);

        CheckpointLogDTO result = checkpointLogService.addLog(checkpointLogDTO);

        assertNotNull(result);
        assertEquals("Checkpoint reached", result.getMessage());
        verify(logRepository).save(any(CheckpointLog.class));
    }

    @Test
    void testUpdateLog_Success() {
        when(logRepository.findById(1L)).thenReturn(Optional.of(log));
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(logRepository.save(any(CheckpointLog.class))).thenReturn(log);

        checkpointLogDTO.setMessage("Updated Checkpoint");

        CheckpointLogDTO result = checkpointLogService.updateLog(1L, checkpointLogDTO);

        assertNotNull(result);
        assertEquals("Updated Checkpoint", result.getMessage());
        verify(logRepository).save(any(CheckpointLog.class));
    }

    @Test
    void testUpdateLog_NotFound() {
        when(logRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CheckpointLogNotFoundException.class, () -> checkpointLogService.updateLog(1L, checkpointLogDTO));
        verify(logRepository).findById(1L);
    }

    @Test
    void testDeleteLogById_Success() {
        when(logRepository.existsById(1L)).thenReturn(true);

        checkpointLogService.deleteLogById(1L);

        verify(logRepository).deleteById(1L);
    }

    @Test
    void testDeleteLogById_NotFound() {
        when(logRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> checkpointLogService.deleteLogById(1L));
        verify(logRepository).existsById(1L);
    }
}