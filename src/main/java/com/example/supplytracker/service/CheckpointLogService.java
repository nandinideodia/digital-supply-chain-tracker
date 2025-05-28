package com.example.supplytracker.service;

import com.example.supplytracker.dto.CheckpointLogDTO;

import java.util.List;

public interface CheckpointLogService {
    List<CheckpointLogDTO> getAllLogs();
    CheckpointLogDTO getLogById(Long id);
    CheckpointLogDTO addLog(CheckpointLogDTO logDTO);
    CheckpointLogDTO updateLog(Long id, CheckpointLogDTO logDTO);
    void deleteLogById(Long id);
}
