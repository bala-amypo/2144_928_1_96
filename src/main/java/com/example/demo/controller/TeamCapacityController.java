package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.TeamCapacity;
import com.example.demo.service.TeamCapacityService;

@RestController
@RequestMapping("/capacities")
public class TeamCapacityController {

    private final TeamCapacityService teamCapacityService;

    public TeamCapacityController(TeamCapacityService teamCapacityService) {
        this.teamCapacityService = teamCapacityService;
    }

    @PostMapping
    public TeamCapacity saveCapacity(@RequestBody TeamCapacity teamCapacity) {
        return teamCapacityService.saveCapacity(teamCapacity);
    }

    @GetMapping("/{id}")
    public TeamCapacity getCapacityById(@PathVariable Long id) {
        return teamCapacityService.getCapacityById(id);
    }

    @GetMapping
    public List<TeamCapacity> getAllCapacities() {
        return teamCapacityService.getAllCapacities();
    }
}
