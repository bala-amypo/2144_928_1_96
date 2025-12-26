package com.example.demo.dto;

import java.time.LocalDate;

public class CapacityAnalysisResultDto {

    private String teamName;
    private LocalDate date;
    private int totalEmployees;
    private int employeesOnLeave;
    private int availableEmployees;
    private boolean capacityBreached;

    public CapacityAnalysisResultDto() {}

    public String getTeamName() {
        return teamName;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public int getEmployeesOnLeave() {
        return employeesOnLeave;
    }

    public int getAvailableEmployees() {
        return availableEmployees;
    }

    public boolean isCapacityBreached() {
        return capacityBreached;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public void setEmployeesOnLeave(int employeesOnLeave) {
        this.employeesOnLeave = employeesOnLeave;
    }

    public void setAvailableEmployees(int availableEmployees) {
        this.availableEmployees = availableEmployees;
    }

    public void setCapacityBreached(boolean capacityBreached) {
        this.capacityBreached = capacityBreached;
    }
}
