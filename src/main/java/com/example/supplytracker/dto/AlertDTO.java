package com.example.supplytracker.dto;
public class AlertDTO {
    private Long id;
    private String type;
    private String message;
    private String severity;
    private String status;
    // Constructors
    public AlertDTO() {}
    public AlertDTO(Long id, String type, String message, String severity, String status) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.severity = severity;
        this.status = status;
    }
    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getSeverity() {
        return severity;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    // toString method
    @Override
    public String toString() {
        return "AlertDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", severity='" + severity + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}