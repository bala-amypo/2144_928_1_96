package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.TeamCapacityConfig;

public interface TeamCapacityRuleService {

    TeamCapacityConfig createRule(TeamCapacityConfig config);

    TeamCapacityConfig updateRule(Long id, TeamCapacityConfig config);

    TeamCapacityConfig getRuleByTeam(String teamName);

    List<TeamCapacityConfig> getAllRules();

    void deleteRule(Long id);
}
