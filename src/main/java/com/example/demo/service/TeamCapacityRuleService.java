package com.example.demo.service;

import com.example.demo.entity.TeamCapacityConfig;

public interface TeamCapacityRuleService {

    TeamCapacityConfig update(Long id, TeamCapacityConfig updatedRule);

    TeamCapacityConfig getRuleByTeam(String teamName);
}
