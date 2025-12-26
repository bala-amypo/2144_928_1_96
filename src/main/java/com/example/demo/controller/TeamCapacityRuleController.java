package com.example.demo.controller;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/capacity-rules")
public class TeamCapacityRuleController {

    private final TeamCapacityRuleService ruleService;

    public TeamCapacityRuleController(TeamCapacityRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public TeamCapacityConfig save(@RequestBody TeamCapacityConfig config) {
        return ruleService.saveConfig(config);
    }

    @GetMapping
    public List<TeamCapacityConfig> getAll() {
        return ruleService.getAllConfigs();
    }
}
