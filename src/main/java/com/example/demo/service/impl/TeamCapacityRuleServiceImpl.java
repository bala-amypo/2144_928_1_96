package com.example.demo.service.impl;

import com.example.demo.entity.TeamCapacityRule;
import com.example.demo.repository.TeamCapacityRuleRepository;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.stereotype.Service;

@Service
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    private final TeamCapacityRuleRepository repo;

    public TeamCapacityRuleServiceImpl(TeamCapacityRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public TeamCapacityRule save(TeamCapacityRule rule) {
        return repo.save(rule);
    }

    @Override
    public TeamCapacityRule getByTeam(String teamName) {
        return repo.findByTeamName(teamName)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }
}
