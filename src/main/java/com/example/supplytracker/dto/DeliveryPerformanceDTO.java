package com.example.supplytracker.dto;

public class DeliveryPerformanceDTO {
    private String supplierName;
    private int totalShipments;
    private int onTimeDeliveries;
    private int delayedDeliveries;

    public DeliveryPerformanceDTO() {}

    public DeliveryPerformanceDTO(String supplierName, int totalShipments, int onTimeDeliveries, int delayedDeliveries) {
        this.supplierName = supplierName;
        this.totalShipments = totalShipments;
        this.onTimeDeliveries = onTimeDeliveries;
        this.delayedDeliveries = delayedDeliveries;
    }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public int getTotalShipments() { return totalShipments; }
    public void setTotalShipments(int totalShipments) { this.totalShipments = totalShipments; }

    public int getOnTimeDeliveries() { return onTimeDeliveries; }
    public void setOnTimeDeliveries(int onTimeDeliveries) { this.onTimeDeliveries = onTimeDeliveries; }

    public int getDelayedDeliveries() { return delayedDeliveries; }
    public void setDelayedDeliveries(int delayedDeliveries) { this.delayedDeliveries = delayedDeliveries; }
}