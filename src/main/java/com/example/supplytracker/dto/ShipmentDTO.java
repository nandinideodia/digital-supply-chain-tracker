package com.example.supplytracker.dto;

import com.example.supplytracker.entity.Shipment.Status;
import java.time.LocalDate;

public class ShipmentDTO {
	//defining all attributes
    private Long id;
    private Long itemId;
    private String fromLocation;
    private String toLocation;
    private LocalDate expectedDelivery;
    private Status currentStatus;
    
    //getters and setters for the attributes
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public LocalDate getExpectedDelivery() {
		return expectedDelivery;
	}
	public void setExpectedDelivery(LocalDate expectedDelivery) {
		this.expectedDelivery = expectedDelivery;
	}
	public Status getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

}
