package com.example.demo.controller;

import com.example.demo.entity.TeamCapacityRule;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-rules")
public class TeamCapacityRuleController {

    private final TeamCapacityRuleService service;

    public TeamCapacityRuleController(TeamCapacityRuleService service) {
        this.service = service;
    }

    @PostMapping
    public TeamCapacityRule create(@RequestBody TeamCapacityRule rule) {
        return service.create(rule);
    }

    @GetMapping("/team/{teamName}")
    public TeamCapacityRule getByTeam(@PathVariable String teamName) {
        return service.getByTeam(teamName);
    }
}
