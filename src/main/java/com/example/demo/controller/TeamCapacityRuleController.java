package com.example.demo.controller;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.service.TeamCapacityRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-rules")
@RequiredArgsConstructor
public class TeamCapacityRuleController {

    private final TeamCapacityRuleService ruleService;

    @PostMapping
    public ResponseEntity<TeamCapacityConfig> createRule(@RequestBody TeamCapacityConfig rule) {
        TeamCapacityConfig createdRule = ruleService.createRule(rule);
        return new ResponseEntity<>(createdRule, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamCapacityConfig> updateRule(@PathVariable Long id, @RequestBody TeamCapacityConfig updatedRule) {
        TeamCapacityConfig rule = ruleService.updateRule(id, updatedRule);
        return ResponseEntity.ok(rule);
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<TeamCapacityConfig> getRuleByTeam(@PathVariable String teamName) {
        TeamCapacityConfig rule = ruleService.getRuleByTeam(teamName);
        return ResponseEntity.ok(rule);
    }
}