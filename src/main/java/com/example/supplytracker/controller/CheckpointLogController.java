package com.example.supplytracker.controller;

import com.example.supplytracker.dto.CheckpointLogDTO;
import com.example.supplytracker.service.CheckpointLogService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkpoints")
@CrossOrigin
public class CheckpointLogController {

    @Autowired
    private CheckpointLogService checkpointLogService;

    @GetMapping
    public List<CheckpointLogDTO> getAllLogs() {
        return checkpointLogService.getAllLogs();
    }

    @GetMapping("/{id}")
    public CheckpointLogDTO getLogById(@PathVariable Long id) {
        return checkpointLogService.getLogById(id);
    }

    @PostMapping
    public CheckpointLogDTO addLog(@Valid @RequestBody CheckpointLogDTO checkpointLog) {
        return checkpointLogService.addLog(checkpointLog);
    }

    @PutMapping("/{id}")
    public CheckpointLogDTO updateLog(@PathVariable Long id, @Valid @RequestBody CheckpointLogDTO updatedLog) {
        return checkpointLogService.updateLog(id, updatedLog);
    }

    @DeleteMapping("/{id}")
    public String deleteLog(@PathVariable Long id) {
        checkpointLogService.deleteLogById(id);
        return "Log deleted";
    }
}
