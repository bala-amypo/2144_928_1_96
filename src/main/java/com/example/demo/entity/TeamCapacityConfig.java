package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_capacity_config")
public class TeamCapacityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private int totalHeadcount;
    private double minCapacityPercent;

    // ✅ GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalHeadcount() {
        return totalHeadcount;
    }

    public void setTotalHeadcount(int totalHeadcount) {
        this.totalHeadcount = totalHeadcount;
    }

    public double getMinCapacityPercent() {
        return minCapacityPercent;
    }

    public void setMinCapacityPercent(double minCapacityPercent) {
        this.minCapacityPercent = minCapacityPercent;
    }
}
