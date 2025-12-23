package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_profiles")
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int employeeId; // ✅ BUSINESS ID

    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String teamName;
    private String role;

    private Boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    // getters & setters
}
