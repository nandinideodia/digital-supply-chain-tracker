package com.example.supplytracker.service;

import com.example.supplytracker.dto.CheckpointLogDTO;
import com.example.supplytracker.entity.CheckpointLog;
import com.example.supplytracker.entity.Shipment;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.CheckpointLogRepository;
import com.example.supplytracker.repository.ShipmentRepository;
import com.example.supplytracker.repository.UserRepository;
import com.example.supplytracker.service.CheckpointLogService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supplytracker.exception.CheckpointLogNotFoundException;
import com.example.supplytracker.exception.InvalidShipmentReferenceException;
import com.example.supplytracker.exception.UserNotAuthorizedException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CheckpointLogServiceImpl implements CheckpointLogService {

    @Autowired
    private CheckpointLogRepository logRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CheckpointLogDTO> getAllLogs() {
        return logRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CheckpointLogDTO getLogById(Long id) {
        CheckpointLog log = logRepository.findById(id)
            .orElseThrow(() -> new CheckpointLogNotFoundException(id));
        return toDto(log);
    }

    @Override
    public CheckpointLogDTO addLog(CheckpointLogDTO dto) {
        CheckpointLog log = toEntity(dto);
        log.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : java.time.LocalDateTime.now());
        return toDto(logRepository.save(log));
    }

    @Override
    public CheckpointLogDTO updateLog(Long id, CheckpointLogDTO dto) {
        CheckpointLog existing = logRepository.findById(id)
            .orElseThrow(() -> new CheckpointLogNotFoundException(id));

        if (dto.getShipmentId() != null) {
            Shipment shipment = shipmentRepository.findById(dto.getShipmentId())
                .orElseThrow(() -> new InvalidShipmentReferenceException(dto.getShipmentId()));
            existing.setShipment(shipment);
        }

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotAuthorizedException(dto.getUserId()));
            existing.setUser(user);
        }

        existing.setMessage(dto.getMessage());
        existing.setTimestamp(dto.getTimestamp());

        return toDto(logRepository.save(existing));
    }

    @Override
    public void deleteLogById(Long id) {
        if (!logRepository.existsById(id)) {
            throw new EntityNotFoundException("CheckpointLog with id " + id + " not found");
        }
        logRepository.deleteById(id);
    }


    private CheckpointLogDTO toDto(CheckpointLog log) {
        CheckpointLogDTO dto = new CheckpointLogDTO();
        dto.setId(log.getId());
        dto.setMessage(log.getMessage());
        dto.setTimestamp(log.getTimestamp());
        dto.setShipmentId(log.getShipment() != null ? log.getShipment().getId() : null);
        dto.setUserId(log.getUser() != null ? log.getUser().getId() : null);
        return dto;
    }

    private CheckpointLog toEntity(CheckpointLogDTO dto) {
        CheckpointLog log = new CheckpointLog();
        log.setMessage(dto.getMessage());

        Shipment shipment = shipmentRepository.findById(dto.getShipmentId())
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found"));
        log.setShipment(shipment);

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            log.setUser(user);
        }

        log.setTimestamp(dto.getTimestamp());
        return log;
    }   
}