package com.example.demo.service;

import com.example.demo.model.TeamCapacityConfig;

import java.util.List;

public interface TeamCapacityRuleService {

    TeamCapacityConfig saveConfig(TeamCapacityConfig config);

    List<TeamCapacityConfig> getAllConfigs();
}
