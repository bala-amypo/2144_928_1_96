package com.example.demo.controller;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.entity.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/capacity-alerts")
public class CapacityAlertController {

    private final CapacityAnalysisService service;

    public CapacityAlertController(CapacityAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public String analyze(@RequestParam String teamName,
                           @RequestParam LocalDate start,
                           @RequestParam LocalDate end) {

        service.analyzeTeamCapacity(teamName, start, end);
        return "Capacity analysis completed";
    }
}

