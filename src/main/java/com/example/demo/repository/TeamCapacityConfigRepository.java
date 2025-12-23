package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TeamCapacityRule;

public interface TeamCapacityConfigRepository extends JpaRepository<TeamCapacityRule, Long> {

    Optional<TeamCapacityRule> findByTeamName(String teamName);
}
