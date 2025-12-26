package com.example.demo.model;

import java.time.LocalDate;

public class LeaveRequest {
    public Long id;
    public LocalDate startDate;
    public LocalDate endDate;
    public String type;
    public String status;

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
