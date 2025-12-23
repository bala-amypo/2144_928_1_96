package com.example.demo.service;

import com.example.demo.entity.TeamCapacityRule;

public interface TeamCapacityRuleService {
    TeamCapacityRule save(TeamCapacityRule rule);
    TeamCapacityRule getByTeam(String teamName);
}
