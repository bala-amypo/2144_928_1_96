package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.entity.TeamCapacityRule;
import com.example.demo.repository.TeamCapacityRuleRepository;
import com.example.demo.service.CapacityAnalysisService;

@Service
@RequiredArgsConstructor
public class CapacityAnalysisServiceImpl
        implements CapacityAnalysisService {

    private final TeamCapacityRuleRepository ruleRepo;

    @Override
    public String analyzeTeamCapacity(String teamName) {
        TeamCapacityRule rule =
                ruleRepo.findByTeamName(teamName).orElse(null);

        if (rule == null) {
            return "No capacity rule defined";
        }
        return "Max leaves per day: " + rule.getMaxLeavesPerDay();
    }
}
