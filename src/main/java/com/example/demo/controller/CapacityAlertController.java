package com.example.demo.controller;

import com.example.demo.entity.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAnalysisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capacity-alerts")
@Tag(name = "Capacity Alerts")
public class CapacityAlertController {

    private final CapacityAnalysisService service;
    private final CapacityAlertRepository repository;

    public CapacityAlertController(CapacityAnalysisService service,
                                   CapacityAlertRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/analyze")
    public void analyze(@RequestParam String teamName) {
        service.analyzeTeamCapacity(teamName, null, null);
    }

    @GetMapping("/team/{teamName}")
    public List<CapacityAlert> getAlerts(@PathVariable String teamName) {
        return repository.findByTeamName(teamName);
    }
}
