package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.service.CapacityAnalysisService;

@RestController
@RequestMapping("/alerts")
public class CapacityAlertController {

    private final CapacityAnalysisService service;

    public CapacityAlertController(CapacityAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public void analyze() {
        service.analyze();
    }

    @GetMapping("/{team}")
    public List<String> getAlerts(@PathVariable String team) {
        return service.getAlerts(team);
    }
}
