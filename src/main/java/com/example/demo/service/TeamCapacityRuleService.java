package com.example.demo.service;

import com.example.demo.entity.TeamCapacityConfig;

public interface TeamCapacityRuleService {

    TeamCapacityConfig createRule(TeamCapacityConfig rule);

    TeamCapacityConfig updateRule(Long id, TeamCapacityConfig rule);

    TeamCapacityConfig getRuleByTeam(String teamName);
}
