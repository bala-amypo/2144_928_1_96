package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long employeeId;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String teamName;
    private String role;
    private Boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    public EmployeeProfile() {}

    // getters & setters
}
