package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private EmployeeProfile employee;

    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String status;
    private String reason;

    // ✅ REQUIRED setters
    public void setEmployee(EmployeeProfile employee) {
        this.employee = employee;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // getters also required
}
