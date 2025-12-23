package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_profiles")
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String fullName;
    private String email;
    private String teamName;
    private String role;
    private boolean active;

    // getters & setters
}
