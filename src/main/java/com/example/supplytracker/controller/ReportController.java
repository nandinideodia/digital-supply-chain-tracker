package com.example.supplytracker.controller;

public class ReportController {

}
package com.example.supplytracker.controller;

import com.example.supplytracker.dto.DeliveryPerformanceDTO;
import com.example.supplytracker.entity.Shipment;
import com.example.supplytracker.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/delivery-performance")
    public ResponseEntity<List<DeliveryPerformanceDTO>> getDeliveryPerformance() {
        return ResponseEntity.ok(reportService.getPerformancePerSupplier());
    }

    @GetMapping("/delayed-shipments")
    public ResponseEntity<List<Shipment>> getDelayedShipments() {
        return ResponseEntity.ok(reportService.getDelayedShipments());
    }
}