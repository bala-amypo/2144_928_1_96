package com.example.demo.dto;

public class CapacityAnalysisResultDto {
    private String teamName;
    private boolean capacityOk;
    private String message;

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public boolean isCapacityOk() { return capacityOk; }
    public void setCapacityOk(boolean capacityOk) { this.capacityOk = capacityOk; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
