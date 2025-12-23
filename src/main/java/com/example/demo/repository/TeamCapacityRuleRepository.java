package com.example.demo.repository;

import com.example.demo.entity.TeamCapacityRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamCapacityRuleRepository extends JpaRepository<TeamCapacityRule, Long> {
    Optional<TeamCapacityRule> findByTeamName(String teamName);
}
