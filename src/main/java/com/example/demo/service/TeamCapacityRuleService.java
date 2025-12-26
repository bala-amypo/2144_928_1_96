package com.example.demo.service;

import com.example.demo.model.TeamCapacityConfig;
import java.util.List;

public interface TeamCapacityRuleService {
    TeamCapacityConfig create(TeamCapacityConfig rule);
    TeamCapacityConfig update(Long id, TeamCapacityConfig rule);
    TeamCapacityConfig getByTeamName(String teamName);
    List<TeamCapacityConfig> getAll();
}