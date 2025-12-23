package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TeamCapacityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private int maxLeavePerDay;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public int getMaxLeavePerDay() { return maxLeavePerDay; }
    public void setMaxLeavePerDay(int maxLeavePerDay) { this.maxLeavePerDay = maxLeavePerDay; }
}
