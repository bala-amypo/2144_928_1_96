package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TeamCapacityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String teamName;

    private Integer totalHeadcount;
    private Integer minCapacityPercent;

    public TeamCapacityConfig() {}

    // getters & setters
}
