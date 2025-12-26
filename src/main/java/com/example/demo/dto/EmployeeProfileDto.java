package com.example.demo.dto;

public class EmployeeProfileDto {

    private String employeeId;
    private String fullName;
    private String email;
    private String teamName;
    private String role;

    public EmployeeProfileDto() {}

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getRole() {
        return role;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
