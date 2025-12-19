package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_capacity_config", uniqueConstraints = {
        @UniqueConstraint(columnNames = "teamName")
})
public class TeamCapacityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private Integer totalHeadcount;
    private Integer minCapacityPercent;

    public TeamCapacityConfig() {}

    public TeamCapacityConfig(String teamName, Integer totalHeadcount, Integer minCapacityPercent) {
        this.teamName = teamName;
        this.totalHeadcount = totalHeadcount;
        this.minCapacityPercent = minCapacityPercent;
    }

    public Long getId() { return id; }
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
