package com.example.demo.model;

public class TeamCapacity {

    private Long teamId;
    private int availableCapacity;

    public TeamCapacity(Long teamId, int availableCapacity) {
        this.teamId = teamId;
        this.availableCapacity = availableCapacity;
    }

    public Long getTeamId() { return teamId; }
    public int getAvailableCapacity() { return availableCapacity; }
}
