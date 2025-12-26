package com.example.demo.controller;

import com.example.demo.model.CapacityAlert;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class CapacityAlertController {

    private final CapacityAnalysisService analysisService;

    public CapacityAlertController(CapacityAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping
    public List<CapacityAlert> getAlerts() {
        return analysisService.getAllAlerts();
    }
}
