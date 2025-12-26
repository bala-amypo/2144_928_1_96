package com.example.demo.dto;

import java.time.LocalDate;

public class LeaveRequestDto {

    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String reason;

    public LeaveRequestDto() {}

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
