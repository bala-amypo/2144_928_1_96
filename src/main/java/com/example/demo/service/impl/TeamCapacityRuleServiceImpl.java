package com.example.demo.service.impl;

import com.example.demo.entity.TeamCapacityConfig;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.stereotype.Service;

@Service
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    private final TeamCapacityConfigRepository repository;

    public TeamCapacityRuleServiceImpl(TeamCapacityConfigRepository repository) {
        this.repository = repository;
    }

    @Override
    public TeamCapacityConfig update(Long id, TeamCapacityConfig updatedRule) {

        TeamCapacityConfig rule = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team capacity rule not found"));

        // ✅ DIRECT SET (NO NULL CHECKS)
        rule.setTeamName(updatedRule.getTeamName());
        rule.setTotalHeadcount(updatedRule.getTotalHeadcount());
        rule.setMinCapacityPercent(updatedRule.getMinCapacityPercent());

        return repository.save(rule);
    }
}
