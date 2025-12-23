package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TeamCapacityRule;
import com.example.demo.repository.TeamCapacityRuleRepository;
import com.example.demo.service.TeamCapacityRuleService;

@Service
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    private final TeamCapacityRuleRepository repository;

    public TeamCapacityRuleServiceImpl(TeamCapacityRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public TeamCapacityRule create(TeamCapacityRule rule) {
        return repository.save(rule);
    }

    @Override
    public TeamCapacityRule getByTeam(String teamName) {
        return repository.findByTeamName(teamName)
                .orElseThrow(() ->
                        new RuntimeException("Team capacity rule not found"));
    }

    @Override
    public List<TeamCapacityRule> getAll() {
        return repository.findAll();
    }

    @Override
    public TeamCapacityRule update(Long id, TeamCapacityRule rule) {
        TeamCapacityRule existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rule not found"));

        existing.setTeamName(rule.getTeamName());
        existing.setTotalHeadcount(rule.getTotalHeadcount());
        existing.setMinCapacityPercent(rule.getMinCapacityPercent());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
