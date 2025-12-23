package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TeamCapacityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String teamName;

    private Integer totalHeadcount;
    private Integer minCapacityPercent;

    // REQUIRED getter
    public Integer getTotalHeadcount() {
        return totalHeadcount;
    }

    public Integer getMinCapacityPercent() {
        return minCapacityPercent;
    }
}
