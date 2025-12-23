package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TeamCapacityRule;

@Repository
public interface TeamCapacityRuleRepository
        extends JpaRepository<TeamCapacityRule, Long> {

    Optional<TeamCapacityRule> findByTeamName(String teamName);
}
