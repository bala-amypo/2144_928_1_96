package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "employee_profile")
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Long, PK, Auto-Generated

    @Column(unique = true, nullable = false)
    private String employeeId; // employeeId (String, unique)

    private String fullName;
    
    @Column(unique = true, nullable = false)
    private String email; // email (String, unique)
    
    private String teamName;
    private String role;
    
    private boolean active = true; // active (Boolean, default to true)
    private LocalDateTime createdAt = LocalDateTime.now(); // createdAt (LocalDateTime)

    @ManyToMany
    @JoinTable(
        name = "employee_colleagues",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "colleague_id")
    )
    private Set<EmployeeProfile> colleagues; // Many-to-Many self-relationship

    // Getters and Setters (Only including those needed by the tests)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    // ... other getters/setters for JPA/Hibernate
}