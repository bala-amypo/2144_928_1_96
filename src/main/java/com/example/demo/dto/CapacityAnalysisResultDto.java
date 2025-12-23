package com.example.demo.dto;

public class CapacityAnalysisResultDto {

    private String teamName;
    private int totalEmployees;
    private int onLeave;
    private boolean belowMinimumCapacity;

    // getters & setters
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public int getTotalEmployees() { return totalEmployees; }
    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public int getOnLeave() { return onLeave; }
    public void setOnLeave(int onLeave) { this.onLeave = onLeave; }

    public boolean isBelowMinimumCapacity() { return belowMinimumCapacity; }
    public void setBelowMinimumCapacity(boolean belowMinimumCapacity) {
        this.belowMinimumCapacity = belowMinimumCapacity;
    }
}
