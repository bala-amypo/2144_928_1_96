package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TeamCapacityRule;
import com.example.demo.repository.TeamCapacityRuleRepository;
import com.example.demo.service.CapacityAnalysisService;

@Service
public class CapacityAnalysisServiceImpl
        implements CapacityAnalysisService {

    private final TeamCapacityRuleRepository repository;
    private final List<String> alerts = new ArrayList<>();

    public CapacityAnalysisServiceImpl(
            TeamCapacityRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void analyzeTeamCapacity(String teamName,
                                    LocalDate start,
                                    LocalDate end) {

        TeamCapacityRule rule =
                repository.findByTeamName(teamName);

        if (rule != null && rule.getMinCapacityPercent() > 50) {
            alerts.add("⚠ Capacity risk for team: " + teamName);
        }
    }

    @Override
    public List<String> getAlerts(String teamName) {
        return alerts;
    }
}
