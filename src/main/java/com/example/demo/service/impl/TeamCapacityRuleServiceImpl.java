package com.example.demo.service.impl;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.TeamCapacityRuleService;

import java.util.List;

public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    private final TeamCapacityConfigRepository repo;

    public TeamCapacityRuleServiceImpl(TeamCapacityConfigRepository repo) {
        this.repo = repo;
    }

    @Override
    public TeamCapacityConfig saveConfig(TeamCapacityConfig config) {
        return repo.save(config);
    }

    @Override
    public List<TeamCapacityConfig> getAllConfigs() {
        return repo.findAll();
    }
}
