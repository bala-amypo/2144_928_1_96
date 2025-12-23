package com.example.demo.controller;

import com.example.demo.entity.TeamCapacityRule;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team-capacity-rules")
@CrossOrigin
public class TeamCapacityRuleController {

    private final TeamCapacityRuleService service;

    public TeamCapacityRuleController(TeamCapacityRuleService service) {
        this.service = service;
    }

    @PostMapping
    public TeamCapacityRule create(@RequestBody TeamCapacityRule rule) {
        return service.create(rule);
    }

    @GetMapping
    public List<TeamCapacityRule> getAll() {
        return service.getAll();
    }

    @GetMapping("/{teamName}")
    public TeamCapacityRule getByTeam(@PathVariable String teamName) {
        return service.getByTeam(teamName);
    }

    @PutMapping("/{id}")
    public TeamCapacityRule update(
            @PathVariable Long id,
            @RequestBody TeamCapacityRule rule) {
        return service.update(id, rule);
    }
}
