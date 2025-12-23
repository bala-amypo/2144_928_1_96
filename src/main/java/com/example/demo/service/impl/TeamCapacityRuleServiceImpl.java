package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TeamCapacityRule;
import com.example.demo.repository.TeamCapacityRuleRepository;
import com.example.demo.service.TeamCapacityRuleService;

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
    public List<TeamCapacityRule> getAll() {
        return repo.findAll();
    }

    @Override
    public TeamCapacityRule update(Long id, TeamCapacityRule rule) {
        TeamCapacityRule existing = repo.findById(id).orElseThrow();
        existing.setTeamName(rule.getTeamName());
        existing.setMaxCapacity(rule.getMaxCapacity());
        return repo.save(existing);
    }
}
