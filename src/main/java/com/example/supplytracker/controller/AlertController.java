package com.example.supplytracker.controller;

import com.example.supplytracker.dto.AlertDTO;
import com.example.supplytracker.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {
    @Autowired
    private AlertService alertService;

    // Get all alerts
    @GetMapping
    public ResponseEntity<List<AlertDTO>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    // Create a new alert
    @PostMapping
    public ResponseEntity<AlertDTO> createAlert(@RequestBody AlertDTO alertDTO) {
        return ResponseEntity.ok(alertService.createAlert(alertDTO));
    }

    // Resolve an alert by ID
    @PutMapping("/{id}/resolve")
    public ResponseEntity<AlertDTO> resolveAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}