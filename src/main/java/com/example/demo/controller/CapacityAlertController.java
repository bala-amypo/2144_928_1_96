package com.example.demo.controller;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.entity.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/capacity-alerts")
public class CapacityAlertController {

    private final CapacityAnalysisService service;

    public CapacityAlertController(CapacityAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public void analyze() {
        service.analyze();
    }

    @GetMapping("/team/{teamName}")
    public List<CapacityAlert> getAlerts(@PathVariable String teamName) {
        return service.getAlerts(teamName);
    }
}

