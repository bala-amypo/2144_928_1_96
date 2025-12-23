package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.TeamCapacityRule;

public interface TeamCapacityRuleService {
    TeamCapacityRule save(TeamCapacityRule rule);
    List<TeamCapacityRule> getAll();
    TeamCapacityRule update(Long id, TeamCapacityRule rule);
}
