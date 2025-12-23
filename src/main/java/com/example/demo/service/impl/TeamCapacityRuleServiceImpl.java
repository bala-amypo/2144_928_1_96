package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TeamCapacityConfig;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TeamCapacityRuleRepository;
import com.example.demo.service.TeamCapacityRuleService;

@Service
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    private final TeamCapacityRuleRepository repository;

    public TeamCapacityRuleServiceImpl(TeamCapacityRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public TeamCapacityConfig createRule(TeamCapacityConfig config) {
        return repository.save(config);
    }

    @Override
    public TeamCapacityConfig updateRule(Long id, TeamCapacityConfig config) {
        TeamCapacityConfig existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));

        existing.setTeamName(config.getTeamName());
        existing.setMaxCapacity(config.getMaxCapacity());
        existing.setUtilization(config.getUtilization());

        return repository.save(existing);
    }

    @Override
    public TeamCapacityConfig getRuleByTeam(String teamName) {
        return repository.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found for team: " + teamName));
    }

    @Override
    public List<TeamCapacityConfig> getAllRules() {
        return repository.findAll();
    }

    @Override
    public void deleteRule(Long id) {
        repository.deleteById(id);
    }
}
