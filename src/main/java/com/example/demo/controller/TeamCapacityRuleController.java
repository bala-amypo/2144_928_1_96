package com.example.demo.controller;

import com.example.demo.entity.TeamCapacityConfig;
import com.example.demo.service.TeamCapacityRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-rules")
@Tag(name = "Team Capacity Rules", description = "Team Capacity Configuration APIs")
public class TeamCapacityRuleController {
    
    private final TeamCapacityRuleService teamCapacityRuleService;
    
    public TeamCapacityRuleController(TeamCapacityRuleService teamCapacityRuleService) {
        this.teamCapacityRuleService = teamCapacityRuleService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new team capacity rule")
    public ResponseEntity<TeamCapacityConfig> createRule(@RequestBody TeamCapacityConfig rule) {
        TeamCapacityConfig created = teamCapacityRuleService.createRule(rule);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing team capacity rule")
    public ResponseEntity<TeamCapacityConfig> updateRule(
            @PathVariable Long id, @RequestBody TeamCapacityConfig rule) {
        TeamCapacityConfig updated = teamCapacityRuleService.updateRule(id, rule);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/team/{teamName}")
    @Operation(summary = "Get capacity rule by team name")
    public ResponseEntity<TeamCapacityConfig> getRuleByTeam(@PathVariable String teamName) {
        TeamCapacityConfig rule = teamCapacityRuleService.getRuleByTeam(teamName);
        return ResponseEntity.ok(rule);
    }
}