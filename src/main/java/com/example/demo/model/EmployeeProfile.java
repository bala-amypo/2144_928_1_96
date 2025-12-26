package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee_profiles", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "employeeId"),
           @UniqueConstraint(columnNames = "email")
       })
public class EmployeeProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String employeeId;
    
    @Column(nullable = false)
    private String fullName;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String teamName;
    
    @Column(nullable = false)
    private String role;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LeaveRequest> leaveRequests = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "employee_colleagues",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "colleague_id")
    )
    private Set<EmployeeProfile> colleagues = new HashSet<>();
    
    @OneToOne(mappedBy = "employeeProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserAccount userAccount;
    
    public EmployeeProfile() {}
    
    public EmployeeProfile(String employeeId, String fullName, String email, 
                          String teamName, String role) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.teamName = teamName;
        this.role = role;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public Set<LeaveRequest> getLeaveRequests() { return leaveRequests; }
    public void setLeaveRequests(Set<LeaveRequest> leaveRequests) { this.leaveRequests = leaveRequests; }
    
    public Set<EmployeeProfile> getColleagues() { return colleagues; }
    public void setColleagues(Set<EmployeeProfile> colleagues) { this.colleagues = colleagues; }
    
    public UserAccount getUserAccount() { return userAccount; }
    public void setUserAccount(UserAccount userAccount) { this.userAccount = userAccount; }
    
    public void addColleague(EmployeeProfile colleague) {
        this.colleagues.add(colleague);
        colleague.getColleagues().add(this);
    }
    
    public void removeColleague(EmployeeProfile colleague) {
        this.colleagues.remove(colleague);
        colleague.getColleagues().remove(this);
    }
}