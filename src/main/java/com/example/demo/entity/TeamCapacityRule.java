package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_capacity_rules")
public class TeamCapacityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String teamName;

    private Integer totalHeadcount;
    private Integer minCapacityPercent;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public Integer getTotalHeadcount() { return totalHeadcount; }
    public void setTotalHeadcount(Integer totalHeadcount) {
        this.totalHeadcount = totalHeadcount;
    }

    public Integer getMinCapacityPercent() { return minCapacityPercent; }
    public void setMinCapacityPercent(Integer minCapacityPercent) {
        this.minCapacityPercent = minCapacityPercent;
    }
}
