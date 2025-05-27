package com.example.supplytracker.entity;
import com.example.supplytracker.enums.AlertType;

// Importing Packages
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    @ManyToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;
    @Enumerated(EnumType.STRING)
 // Type of alert: DELAY or DAMAGE
    private AlertType type; 
    private String message; 
    private LocalDateTime createdOn; 
    private boolean resolved;
}
