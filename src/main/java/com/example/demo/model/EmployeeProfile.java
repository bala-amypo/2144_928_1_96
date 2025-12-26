package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String employeeId;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String teamName;

    private String role;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
        name = "employee_colleagues",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "colleague_id")
    )
    @JsonIgnore
    private Set<EmployeeProfile> colleagues = new HashSet<>();
    
    // Explicit getter for 'active' to resolve 'isActive()' symbol issue in test code
    public boolean isActive() {
        return this.active != null && this.active;
    }
}