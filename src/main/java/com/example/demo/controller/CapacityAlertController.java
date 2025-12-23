package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.service.CapacityAnalysisService;

@RestController
@RequestMapping("/capacity")
public class CapacityAlertController {

    private final CapacityAnalysisService service;

    public CapacityAlertController(
            CapacityAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public void analyze(@RequestParam String teamName) {
        service.analyzeTeamCapacity(
                teamName,
                LocalDate.now(),
                LocalDate.now().plusDays(5));
    }

    @GetMapping("/alerts/{teamName}")
    public List<String> getAlerts(
            @PathVariable String teamName) {
        return service.getAlerts(teamName);
    }
}
