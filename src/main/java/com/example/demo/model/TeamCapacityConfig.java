package com.example.demo.model;

public class TeamCapacityConfig {
    private Long id;
    private String teamName;
    private int minimumCapacity;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public int getMinimumCapacity() { return minimumCapacity; }
    public void setMinimumCapacity(int minimumCapacity) { this.minimumCapacity = minimumCapacity; }
}
