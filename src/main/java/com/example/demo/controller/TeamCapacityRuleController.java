package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.TeamCapacityConfig;
import com.example.demo.service.TeamCapacityRuleService;

@RestController
@RequestMapping("/api/team-capacity")
public class TeamCapacityRuleController {

    private final TeamCapacityRuleService service;

    public TeamCapacityRuleController(TeamCapacityRuleService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public TeamCapacityConfig createRule(@RequestBody TeamCapacityConfig config) {
        return service.createRule(config);
    }

    // UPDATE
    @PutMapping("/{id}")
    public TeamCapacityConfig updateRule(
            @PathVariable Long id,
            @RequestBody TeamCapacityConfig config) {
        return service.updateRule(id, config);
    }

    // GET BY TEAM
    @GetMapping("/{teamName}")
    public TeamCapacityConfig getByTeam(@PathVariable String teamName) {
        return service.getRuleByTeam(teamName);
    }

    // GET ALL
    @GetMapping
    public List<TeamCapacityConfig> getAll() {
        return service.getAllRules();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteRule(id);
    }
}
