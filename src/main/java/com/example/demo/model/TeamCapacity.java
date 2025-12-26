package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "team_capacity")
public class TeamCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teamId;
    private Integer availableCapacity;

    public TeamCapacity() {
    }

    public TeamCapacity(Long id, Long teamId, Integer availableCapacity) {
        this.id = id;
        this.teamId = teamId;
        this.availableCapacity = availableCapacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(Integer availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
}
