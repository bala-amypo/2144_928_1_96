package com.example.demo.dto;

public class EmployeeProfileDto {

    private Long id;
    private int employeeId;   // ✅ MUST be int
    private String fullName;
    private String email;
    private String teamName;
    private String role;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
