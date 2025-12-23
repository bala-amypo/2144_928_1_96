package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TeamCapacityConfig;

public interface TeamCapacityRuleRepository
        extends JpaRepository<TeamCapacityConfig, Long> {

    Optional<TeamCapacityConfig> findByTeamName(String teamName);
}
