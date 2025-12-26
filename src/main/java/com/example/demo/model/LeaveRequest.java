package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    private LocalDate startDate;

    private LocalDate endDate;

    private String type; // ANNUAL, SICK, etc.

    @Column(columnDefinition = "VARCHAR(255) default 'PENDING'")
    private String status; // PENDING, APPROVED, REJECTED

    private String reason;
}