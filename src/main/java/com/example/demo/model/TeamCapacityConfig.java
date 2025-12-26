package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "team_capacity_config")
public class TeamCapacityConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String teamName;
    
    private double minCapacityPercent = 75.0;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public double getMinCapacityPercent() { return minCapacityPercent; }
    public void setMinCapacityPercent(double minCapacityPercent) { this.minCapacityPercent = minCapacityPercent; }
}