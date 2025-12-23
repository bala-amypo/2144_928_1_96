package com.example.demo.controller;

import com.example.demo.entity.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/capacity-alerts")
@CrossOrigin
public class CapacityAlertController {

    private final CapacityAlertRepository repository;

    public CapacityAlertController(CapacityAlertRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CapacityAlert> getAll() {
        return repository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public List<CapacityAlert> getByTeam(@PathVariable String teamName) {
        return repository.findByTeamName(teamName);
    }
}
