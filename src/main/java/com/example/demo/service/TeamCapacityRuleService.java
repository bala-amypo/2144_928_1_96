package com.example.demo.service;

import com.example.demo.entity.TeamCapacityRule;

public interface TeamCapacityRuleService {
    TeamCapacityRule create(TeamCapacityRule rule);
    TeamCapacityRule getByTeam(String teamName);
}
